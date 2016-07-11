package ho.jackie.flickrfun.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ho.jackie.flickrfun.R;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNetworkLost() {

    }

    @Override
    public void onNetworkReconnect() {

    }

    @Override
    public void onSearchSuccess() {

    }

    @Override
    public void onSearchFail() {

    }

    @Override
    public void showImage() {

    }

    @Override
    public void hideImage() {

    }
}
