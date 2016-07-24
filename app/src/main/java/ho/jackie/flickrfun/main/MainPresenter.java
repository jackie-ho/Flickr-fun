package ho.jackie.flickrfun.main;

import android.content.SharedPreferences;
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
import ho.jackie.flickrfun.retrofit.model.FlickrResult;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JHADI on 7/10/16.
 */
public class MainPresenter implements MainContract.ActionListener {

    Retrofit mRetrofit;

    private FlickrApi mFlickrApi;

    private Subscription mFlickrSubscription;

    private final WeakReference<MainContract.View> mainView;

    private Map<String, String> queryMap;

    public MainPresenter(@NonNull MainContract.View view, SharedPreferences sharedPreferences, Retrofit retrofit){
        this.mainView = new WeakReference<MainContract.View>(view);
        queryMap = new HashMap<>();
        mRetrofit = retrofit;
        mFlickrApi = mRetrofit.create(FlickrApi.class);
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
        queryMap.put("tags",query);
        queryMap.put("method", "flickr.photos.search");
        mFlickrSubscription = mFlickrApi.images(queryMap)
                .take(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlickrResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.get().onSearchFail();
                    }

                    @Override
                    public void onNext(FlickrResult flickrResult) {
                        FlickrPhotos flickrPhotos;
                        if (flickrResult != null) {
                            flickrPhotos = flickrResult.getSearchResult();
                            if (flickrPhotos.getTotal() > 0) {
                                mainView.get().onSearchSuccess(flickrPhotos);
                            } else {
                                onError(new Throwable());
                            }
                        } else {
                            onError(new Throwable());
                        }

                    }
                });
    }
}
