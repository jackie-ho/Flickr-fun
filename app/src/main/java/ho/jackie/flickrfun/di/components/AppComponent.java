package ho.jackie.flickrfun.di.components;

import android.app.Application;
import android.content.SharedPreferences;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import dagger.Component;
import ho.jackie.flickrfun.app.MyApp;
import ho.jackie.flickrfun.di.modules.AppModule;
import ho.jackie.flickrfun.di.scopes.AppScope;
import retrofit2.Retrofit;

@Component(modules = AppModule.class)
@AppScope
public interface AppComponent {

    EventBus eventbus();
    Retrofit retrofit();
    SharedPreferences sharedPreferences();

    void inject(MyApp application);
}
