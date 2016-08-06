package ho.jackie.flickrfun.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.inputmethod.InputMethodManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ho.jackie.flickrfun.retrofit.FlickrApi;
import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrResult;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainPresenter implements MainContract.ActionListener {

    private final Retrofit mRetrofit;

    private FlickrApi mFlickrApi;
    private Subscription mFlickrSubscription;
    private final WeakReference<MainContract.View> mainView;
    private Map<String, String> queryMap;
    private SharedPreferences mSharedPrefs;


    public MainPresenter(@NonNull MainContract.View view, @NonNull SharedPreferences sharedPreferences, @NonNull Retrofit retrofit) {
        this.mainView = new WeakReference<MainContract.View>(view);
        this.mSharedPrefs = sharedPreferences;
        queryMap = new HashMap<>();
        mRetrofit = retrofit;
        mFlickrApi = mRetrofit.create(FlickrApi.class);
    }


    @Override
    public void onCreate(Bundle cache) {
        if (cache != null) {
            ArrayList<FlickrImages> savedImageList = cache.getParcelableArrayList(MainActivity.SAVED_IMAGES);
            String queryTerm = cache.getString(MainActivity.QUERY);
            mainView.get().loadImage(savedImageList);
            mainView.get().displayNumberOfSearchesText(queryTerm);
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
    public void searchForImages(final String query) {
        if (query.trim().length() == 0){
            mainView.get().onSearchFail("Enter search term");
            return;
        }
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
                        int numSearches = mSharedPrefs.contains(query) ? mSharedPrefs.getInt(query, 0) : 0;
                        mSharedPrefs.edit().putInt(query, ++numSearches).commit();
                        mainView.get().displayNumberOfSearchesText(query);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.get().onSearchFail(e.getMessage());
                    }

                    @Override
                    public void onNext(FlickrResult flickrResult) {
                        if (flickrResult != null && flickrResult.getSearchResult() != null && flickrResult.getSearchResult().getTotal() > 0) {
                            mainView.get().onSearchSuccess(flickrResult);
                        } else {
                            onError(new Throwable());
                        }
                    }
                });
    }


}
