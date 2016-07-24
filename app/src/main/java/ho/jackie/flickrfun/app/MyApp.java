package ho.jackie.flickrfun.app;

import android.app.Application;

import ho.jackie.flickrfun.di.components.AppComponent;
import ho.jackie.flickrfun.di.components.DaggerAppComponent;
import ho.jackie.flickrfun.di.modules.AppModule;

/**
 * Created by JHADI on 7/12/16.
 */
public class MyApp extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
