package ho.jackie.flickrfun.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ho.jackie.flickrfun.BuildConfig;
import ho.jackie.flickrfun.R;
import ho.jackie.flickrfun.app.MyApp;
import ho.jackie.flickrfun.di.scopes.AppScope;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


@Module
public class AppModule {

    private final Application app;

    private static final String PREFERENCES = "myAppPrefs";
    private final String BASE_URL = "https://api.flickr.com/services/";


    private final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

    private RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
            .createWithScheduler(Schedulers.io());
    private final Retrofit.Builder builder = new Retrofit.Builder()
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL);


    public AppModule(MyApp app) {
        this.app = app;
    }

    @Provides
    @AppScope
    public SharedPreferences providesSharedPreferences(@Named("AppContext") Context appContext) {
        return appContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    @Provides
    @AppScope
    @Named("AppContext")
    public Context providesAppContext() {
        return app;
    }

    @Provides
    @AppScope
    public EventBus providesEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @AppScope
    public Retrofit providesFlickrRetrofit() {
        return builder.client(setupOkHttp()).build();
    }

    private OkHttpClient setupOkHttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                //Add api key
                HttpUrl urlWithKey = original.url().newBuilder()
                        .addQueryParameter("api_key", BuildConfig.FLICKR_API_KEY)
                        .addQueryParameter("format", "json")
                        .addQueryParameter("nojsoncallback","1")
                        .build();

                Request.Builder requestBuilder = original.newBuilder()
                        .method(original.method(), original.body())
                        .url(urlWithKey);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        okHttpClient.addInterceptor(loggingInterceptor);
        return okHttpClient.build();
    }
}
