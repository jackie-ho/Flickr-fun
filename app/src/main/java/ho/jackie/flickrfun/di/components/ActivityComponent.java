package ho.jackie.flickrfun.di.components;

import dagger.Component;
import ho.jackie.flickrfun.di.modules.ActivityModule;
import ho.jackie.flickrfun.di.scopes.ActivityScope;
import ho.jackie.flickrfun.main.MainActivity;


@Component (dependencies = AppComponent.class, modules = ActivityModule.class)
@ActivityScope
public interface ActivityComponent {

    void inject (MainActivity mainActivity);
}
