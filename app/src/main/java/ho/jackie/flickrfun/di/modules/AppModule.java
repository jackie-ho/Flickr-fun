package ho.jackie.flickrfun.di.modules;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import ho.jackie.flickrfun.di.scopes.AppScope;
import ho.jackie.flickrfun.retrofit.FlickrApi;
import ho.jackie.flickrfun.retrofit.FlickrService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by JHADI on 7/10/16.
 */
@Module
public class AppModule {

    private final Application app;

    private final String BASE_URL = "https://api.flickr.com/services";


    private final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

    private RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
            .createWithScheduler(Schedulers.io());
    private final Retrofit.Builder builder = new Retrofit.Builder()
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL);


    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @AppScope
    public Application providesAppContext() {
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
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "something")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return okHttpClient.build();
    }
}
