package ho.jackie.flickrfun.di.components;

import dagger.Component;
import ho.jackie.flickrfun.di.modules.ActivityModule;
import ho.jackie.flickrfun.main.MainActivity;

/**
 * Created by JHADI on 7/10/16.
 */
@Component (dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject (MainActivity mainActivity);
}
