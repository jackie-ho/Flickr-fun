package ho.jackie.flickrfun.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ho.jackie.flickrfun.R;
import ho.jackie.flickrfun.app.MyApp;
import ho.jackie.flickrfun.di.components.DaggerActivityComponent;
import ho.jackie.flickrfun.di.modules.ActivityModule;
import ho.jackie.flickrfun.retrofit.model.FlickrImages;
import ho.jackie.flickrfun.retrofit.model.FlickrPhotos;
import ho.jackie.flickrfun.retrofit.model.FlickrResult;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    public static final String SAVED_IMAGES = "currentImages";
    public static final String QUERY = "queryItem";
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.image_search_edit)
    EditText searchEditText;
    @BindView(R.id.submit_search)
    Button submitButton;
    @BindView(R.id.search_events_textview)
    TextView numberSearchesText;
    @BindViews({R.id.flickr_image1, R.id.flickr_image2, R.id.flickr_image3, R.id.flickr_image4})
    List<ImageView> flickrImages;

    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    Retrofit mRetrofit;

    private MainPresenter mPresenter;
    private Picasso.Builder picassoBuilder;
    private ArrayList<FlickrImages> flickrImagesList;
    private boolean imageLoaded;
    private String queryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerActivityComponent.builder()
                .appComponent(((MyApp) getApplicationContext()).getAppComponent())
                .activityModule(new ActivityModule(this, this))
                .build()
                .inject(this);

        mPresenter = new MainPresenter(this, mSharedPreferences, mRetrofit);

        mPresenter.onCreate(savedInstanceState);

    }

    @Override
    public void onNetworkLost() {
        //display textview and progressbar
        submitButton.setEnabled(false);
    }

    @Override
    public void onNetworkReconnect() {
        //hide textview and progressbar, display image if there is one
        submitButton.setEnabled(true);
    }

    @Override
    public void onSearchSuccess(FlickrResult result) {
        if (result != null) {
            loadImage(result.getSearchResult().getPhotos());
        }

    }

    @Override
    public void onSearchFail(String error) {
        Toast.makeText(MainActivity.this, "Search failed. \n" + error, Toast.LENGTH_SHORT).show();
        Log.e(TAG, error);
        submitButton.setEnabled(true);
    }

    @Override
    public void showImageLoadFailure(String error) {
        //make error textview visible
    }

    @Override
    public void showImages() {
        if (flickrImages.size() > 0) {
            for (ImageView flickrImage : flickrImages) {
                flickrImage.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void hideImages() {
        if (flickrImages.size() > 0) {
            for (ImageView flickrImage : flickrImages) {
                flickrImage.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onSearchStart(String query) {
        mPresenter.searchForImages(query);
        submitButton.setEnabled(false);
    }

    @Override
    public void loadImage(ArrayList<FlickrImages> photos) {

        if (photos == null) {
            return;
        }

        flickrImagesList = photos;

        for (int i = 0; i < 4; i++) {

            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("https://farm");
            urlBuilder.append(flickrImagesList.get(i).getFarm());
            urlBuilder.append(".staticflickr.com/");
            urlBuilder.append(flickrImagesList.get(i).getServer());
            urlBuilder.append("/" + flickrImagesList.get(i).getId() + "_");
            urlBuilder.append(flickrImagesList.get(i).getSecret() + ".jpg");

            Picasso.with(this)
                    .load(urlBuilder.toString())
                    .into(flickrImages.get(i));

        }
        submitButton.setEnabled(true);
        imageLoaded = true;
    }

    @Override
    public void displayNumberOfSearchesText(String query) {
        queryItem = query;
        int number = mSharedPreferences.getInt(query,-1);
        Resources res = getResources();
        numberSearchesText.setText(res.getQuantityString(R.plurals.number_of_searches, number, number, query));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (imageLoaded){
            outState.putParcelableArrayList(SAVED_IMAGES, flickrImagesList);
            outState.putString(QUERY, queryItem);
        }
        super.onSaveInstanceState(outState);
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

    @OnClick(R.id.submit_search)
    void submitQuery() {
        onSearchStart(searchEditText.getText().toString());
        searchEditText.clearFocus();
    }


}
