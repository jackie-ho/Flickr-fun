package ho.jackie.flickrfun.main;

/**
 * Created by JHADI on 7/10/16.
 */
public interface MainContract {

    interface View{
        void onNetworkLost();
        void onNetworkReconnect();
        void onSearchSuccess(String imageUrl);
        void onSearchFail();
        void showImage();
        void hideImage();
        void onSearchStart(String query);
        void onSearchFinished();
    }

    interface ActionListener{
        void addPicture();
        void onResume();
        void onPause();
        void onDestroy();
        void searchForImages(String query);
    }
}