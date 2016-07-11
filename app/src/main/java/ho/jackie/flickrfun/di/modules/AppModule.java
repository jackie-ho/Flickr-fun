package ho.jackie.flickrfun.di.modules;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import dagger.Module;
import dagger.Provides;
import ho.jackie.flickrfun.di.scopes.AppScope;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JHADI on 7/10/16.
 */
@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app){
        this.app = app;
    }
    @Provides
    @AppScope
    public Application providesAppContext(){
        return app;
    }

    @Provides
    @AppScope
    public EventBus providesEventBus(){
        return EventBus.getDefault();
    }

    @Provides
    @AppScope
    public Retrofit providesRetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
