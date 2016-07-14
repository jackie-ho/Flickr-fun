package ho.jackie.flickrfun.main;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by JHADI on 7/10/16.
 */
public class MainPresenter implements MainContract.ActionListener {

    private final WeakReference<MainContract.View> mainView;

    public MainPresenter(@NonNull MainContract.View view){
        this.mainView = new WeakReference<MainContract.View>(view);
    }

    @Override
    public void addPicture() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mainView.clear();
    }

    @Override
    public void searchForImages(String query) {

    }
}
