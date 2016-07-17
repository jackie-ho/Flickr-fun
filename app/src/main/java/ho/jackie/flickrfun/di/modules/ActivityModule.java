package ho.jackie.flickrfun.di.modules;

import android.content.Context;
import android.net.Uri;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import ho.jackie.flickrfun.di.scopes.ActivityScope;
import ho.jackie.flickrfun.main.MainContract;
import ho.jackie.flickrfun.main.MainPresenter;

/**
 * Created by JHADI on 7/17/16.
 */
@Module
public class ActivityModule {

    private final Context mContext;
    private final MainContract.View view;

    public ActivityModule(Context context, MainContract.View view){
        this.mContext = context;
        this.view = view;
    }

    @Provides
    @ActivityScope
    public Picasso providesPicassoBuilder(){
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                view.showImageLoadFailure(exception.getMessage());
            }
        });

        return builder.build();
    }

    @Provides
    @ActivityScope
    public MainPresenter providesPresenter(){
       return new MainPresenter(view);
    }
}
