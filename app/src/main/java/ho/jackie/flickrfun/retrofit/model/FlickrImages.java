package ho.jackie.flickrfun.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;


public class FlickrImages implements Parcelable{

    String id;
    String owner;
    String secret;
    String server;
    String farm;
    String title;
    int ispublic;
    int isfriend;
    int isfamily;

    protected FlickrImages(Parcel in) {
        id = in.readString();
        owner = in.readString();
        secret = in.readString();
        server = in.readString();
        farm = in.readString();
        title = in.readString();
        ispublic = in.readInt();
        isfriend = in.readInt();
        isfamily = in.readInt();
    }

    public static final Creator<FlickrImages> CREATOR = new Creator<FlickrImages>() {
        @Override
        public FlickrImages createFromParcel(Parcel in) {
            return new FlickrImages(in);
        }

        @Override
        public FlickrImages[] newArray(int size) {
            return new FlickrImages[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(owner);
        dest.writeString(secret);
        dest.writeString(server);
        dest.writeString(farm);
        dest.writeString(title);
        dest.writeInt(ispublic);
        dest.writeInt(isfriend);
        dest.writeInt(isfamily);
    }
}
