package ho.jackie.flickrfun.main;

/**
 * Created by JHADI on 7/10/16.
 */
public interface MainContract {

    interface View{
        void onNetworkLost();
        void onNetworkReconnect();
        void onSearchSuccess();
        void onSearchFail();
        void showImage();
        void hideImage();
    }

    interface ActionListener{
        void openPicture();
        void addPicture();
    }
}
