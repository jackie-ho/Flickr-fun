package ho.jackie.flickrfun.main;

import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrPhotos;

/**
 * Created by JHADI on 7/10/16.
 */
public interface MainContract {

    interface View{
        void onNetworkLost();
        void onNetworkReconnect();
        void onSearchSuccess(FlickrPhotos photos);
        void onSearchFail(String error);
        void showImageLoadFailure(String errorMessage);
        void showImages();
        void hideImages();
        void onSearchStart(String query);

    }

    interface ActionListener{
        void onResume();
        void onPause();
        void onDestroy();
        void searchForImages(String query);
    }
}
