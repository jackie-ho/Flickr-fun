package ho.jackie.flickrfun.retrofit.model;

import java.util.ArrayList;
import java.util.List;


public class FlickrPhotos {

    int page;
    int pages;
    int perpage;
    int total;
    ArrayList<FlickrImages> photo;

    public ArrayList<FlickrImages> getPhotos() {
        return photo;
    }

    public int getTotal(){
        return total;
    }
}
