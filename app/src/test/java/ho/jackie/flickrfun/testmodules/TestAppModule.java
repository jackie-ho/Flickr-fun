package ho.jackie.flickrfun.testmodules;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.fakes.RoboSharedPreferences;

import javax.inject.Named;

import ho.jackie.flickrfun.app.MyApp;
import ho.jackie.flickrfun.di.modules.AppModule;
import ho.jackie.flickrfun.testapp.TestApplication;
import retrofit2.Retrofit;

/**
 * Created by JHADI on 7/26/16.
 */
public class TestAppModule extends AppModule {

    private final TestApplication testApplication;

    public TestAppModule(MyApp app) {
        super(app);
        testApplication = (TestApplication) app;
    }

    @Override
    public Context providesAppContext() {
        return testApplication.getApplicationContext();
    }

    @Override
    public Retrofit providesFlickrRetrofit() {
        return Mockito.mock(Retrofit.class);
    }

    @Override
    public EventBus providesEventBus() {
        return Mockito.mock(EventBus.class);
    }

    @Override
    public SharedPreferences providesSharedPreferences(@Named("AppContext") Context appContext) {
        //Robosharedpreferences
        return null;
    }
}
