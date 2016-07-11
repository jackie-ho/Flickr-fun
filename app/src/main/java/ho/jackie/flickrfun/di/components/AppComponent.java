package ho.jackie.flickrfun.di.components;

import android.app.Application;

import dagger.Component;
import ho.jackie.flickrfun.di.modules.AppModule;

@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(Application application);
}
