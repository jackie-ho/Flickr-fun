package ho.jackie.flickrfun.main;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import ho.jackie.flickrfun.retrofit.FlickrApi;
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

    private SharedPreferences mSharedPrefs;

    private FlickrResult flickrSavedResult;

    public MainPresenter(@NonNull MainContract.View view, @NonNull SharedPreferences sharedPreferences, @NonNull Retrofit retrofit) {
        this.mainView = new WeakReference<MainContract.View>(view);
        this.mSharedPrefs = sharedPreferences;
        queryMap = new HashMap<>();
        mRetrofit = retrofit;
        mFlickrApi = mRetrofit.create(FlickrApi.class);
    }


    @Override
    public void onResume() {
        if (flickrSavedResult != null) {
            mainView.get().loadImage(flickrSavedResult.getSearchResult());
        }
    }

    @Override
    public void onPause() {
        //unsubscribe subscriptions
        if (mFlickrSubscription != null) {
            mFlickrSubscription.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        mainView.clear();

    }

    @Override
    public void searchForImages(String query) {
        queryMap.clear();
        queryMap.put("tags", query);
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
                        mainView.get().onSearchFail(e.getMessage());
                    }

                    @Override
                    public void onNext(FlickrResult flickrResult) {
                        if (flickrResult != null & flickrResult.getSearchResult().getTotal() > 0) {
                            mainView.get().onSearchSuccess(flickrResult);
                            flickrSavedResult = flickrResult;
                        } else {
                            onError(new Throwable());
                        }
                    }
                });
    }
}
