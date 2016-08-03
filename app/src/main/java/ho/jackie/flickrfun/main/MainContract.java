package ho.jackie.flickrfun.main;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrPhotos;
import ho.jackie.flickrfun.retrofit.model.FlickrResult;

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
        void loadImage(ArrayList<FlickrImages> photos);
        void displayNumberOfSearchesText(String query);

    }

    interface ActionListener{
        void onCreate(Bundle cache);
        void onPause();
        void onDestroy();
        void searchForImages(String query);
    }
}
