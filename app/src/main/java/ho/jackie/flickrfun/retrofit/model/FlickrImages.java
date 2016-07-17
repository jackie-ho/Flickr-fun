package ho.jackie.flickrfun.retrofit.model;

/**
 * Created by JHADI on 7/11/16.
 */
public class FlickrImages {

    String id;
    String owner;
    String secret;
    String server;
    String farm;
    String title;
    int ispublic;
    int isfriend;
    int isfamily;

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public String getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public int getIspublic() {
        return ispublic;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public int getIsfamily() {
        return isfamily;
    }
}
