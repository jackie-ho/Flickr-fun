package ho.jackie.flickrfun.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ho.jackie.flickrfun.R;
import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrPhotos;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @BindView(R.id.image_search_edit)
    EditText searchEditText;
    @BindView(R.id.submit_search)
    Button submitButton;
    @BindViews({R.id.flickr_image1,R.id.flickr_image2, R.id.flickr_image3, R.id.flickr_image4})
    List<ImageView> flickrImages;


    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);

    }

    @Override
    public void onNetworkLost() {
        //display textview and progressbar
    }

    @Override
    public void onNetworkReconnect() {
    //hide textview and progressbar, display image if there is one
    }

    @Override
    public void onSearchSuccess(FlickrPhotos photos) {

        List<FlickrImages> flickrImagesList = photos.getPhotos();

        for (FlickrImages image : flickrImagesList) {

            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("https://farm");
            urlBuilder.append(image.getFarm());
            urlBuilder.append(".staticflickr.com/");
            urlBuilder.append(image.getServer());
            urlBuilder.append("/" + image.getId() + "_");
            urlBuilder.append(image.getSecret() + ".jpg");
        }

        Picasso.with(this)
                .load(urlBuilder.toString())
                .into(flickrImage);
    }

    @Override
    public void onSearchFail() {
        Toast.makeText(MainActivity.this, "Search failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showImage() {
        flickrImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImage() {
        flickrImage.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSearchStart(String query) {
        mPresenter.searchForImages(query);
    }

    @Override
    public void onSearchFinished() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
