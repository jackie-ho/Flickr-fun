package ho.jackie.flickrfun.retrofit;

import java.util.List;

import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by JHADI on 7/11/16.
 */
public interface FlickrApi {


    @GET("addlink/{flickrquery}")
    Observable<List<FlickrImages>> images(@Query("flickrquery") String query);


}
