package ho.jackie.flickrfun.main;

import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrPhotos;
import ho.jackie.flickrfun.retrofit.model.FlickrResult;

/**
 * Created by JHADI on 7/10/16.
 */
public interface MainContract {

    interface View{
        void onNetworkLost();
        void onNetworkReconnect();
        void onSearchSuccess(FlickrResult flickrResult);
        void onSearchFail(String error);
        void showImageLoadFailure(String errorMessage);
        void showImages();
        void hideImages();
        void onSearchStart(String query);
        void loadImage(FlickrPhotos photos);

    }

    interface ActionListener{
        void onResume();
        void onPause();
        void onDestroy();
        void searchForImages(String query);
    }
}
