package ho.jackie.flickrfun.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ho.jackie.flickrfun.R;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @BindView(R.id.image_search_edit)
    EditText searchEditText;
    @BindView(R.id.submit_search)
    Button submitButton;
    @BindView(R.id.flickr_image)
    ImageView flickrImage;


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

    }

    @Override
    public void onNetworkReconnect() {

    }

    @Override
    public void onSearchSuccess(String imageUrl) {
        Picasso.with(this)
                .load(imageUrl)
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
