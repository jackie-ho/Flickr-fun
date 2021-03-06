package ho.jackie.flickrfun.retrofit;

import java.util.List;
import java.util.Map;

import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrPhotos;
import ho.jackie.flickrfun.retrofit.model.FlickrResult;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface FlickrApi {

    @GET("rest/")
    Observable<FlickrResult> images(@QueryMap Map<String, String> options);

}
