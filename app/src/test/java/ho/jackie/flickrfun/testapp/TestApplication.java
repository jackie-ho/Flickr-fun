package ho.jackie.flickrfun.testapp;

import ho.jackie.flickrfun.app.MyApp;
import ho.jackie.flickrfun.di.components.AppComponent;
import ho.jackie.flickrfun.di.components.DaggerAppComponent;
import ho.jackie.flickrfun.di.modules.AppModule;
import ho.jackie.flickrfun.testmodules.TestAppModule;

/**
 * Created by JHADI on 7/26/16.
 */
public class TestApplication extends MyApp {

    private AppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        testAppComponent = DaggerAppComponent.builder()
                .appModule(new TestAppModule(this))
                .build();

    }

    @Override
    public AppComponent getAppComponent() {
        return testAppComponent;
    }
}
