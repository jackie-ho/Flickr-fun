package ho.jackie.flickrfun.main;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ho.jackie.flickrfun.retrofit.FlickrApi;
import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrPhotos;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JHADI on 7/10/16.
 */
public class MainPresenter implements MainContract.ActionListener {

    @Inject
    Retrofit retrofit;

    private FlickrApi mFlickrApi;

    private Subscription mFlickrSubscription;

    private final WeakReference<MainContract.View> mainView;

    private Map<String, String> queryMap;

    public MainPresenter(@NonNull MainContract.View view){
        this.mainView = new WeakReference<MainContract.View>(view);
        queryMap = new HashMap<>();
        mFlickrApi = retrofit.create(FlickrApi.class);
    }

    @Override
    public void addPicture() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {
        //unsubscribe subscriptions
    }

    @Override
    public void onDestroy() {
        mainView.clear();
    }

    @Override
    public void searchForImages(String query) {
        queryMap.clear();
        queryMap.put("query",query);
        mFlickrSubscription = mFlickrApi.images(queryMap, "photos","search")
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .take(4)
                .subscribe(new Observer<FlickrPhotos>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.get().onSearchFail();
                    }

                    @Override
                    public void onNext(FlickrPhotos flickrPhotos) {
                        mainView.get().onSearchSuccess(flickrPhotos);

                    }
                });
    }
}
