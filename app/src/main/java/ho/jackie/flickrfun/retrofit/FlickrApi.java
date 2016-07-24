package ho.jackie.flickrfun.retrofit;

import java.util.List;
import java.util.Map;

import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrPhotos;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by JHADI on 7/11/16.
 */
public interface FlickrApi {


    @GET("rest/")
    Observable<FlickrPhotos> images(@QueryMap Map<String, String> options
                                    );


}
