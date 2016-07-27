package ho.jackie.flickrfun.testmodules;

import android.content.Context;

import ho.jackie.flickrfun.di.modules.ActivityModule;
import ho.jackie.flickrfun.main.MainContract;

/**
 * Created by JHADI on 7/26/16.
 */
public class TestActivityModule extends ActivityModule {

    public TestActivityModule(Context context, MainContract.View view) {
        super(context, view);
    }
}
