package ho.jackie.flickrfun.testapp;

import ho.jackie.flickrfun.app.MyApp;
import ho.jackie.flickrfun.di.components.AppComponent;

/**
 * Created by JHADI on 7/26/16.
 */
public class TestApplication extends MyApp {

    private AppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public AppComponent getAppComponent() {
        return testAppComponent;
    }
}
