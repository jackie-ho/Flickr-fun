package ho.jackie.flickrfun;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import ho.jackie.flickrfun.main.MainActivity;
import ho.jackie.flickrfun.testapp.TestApplication;

@Config(sdk = 21, application = TestApplication.class)
@RunWith(RobolectricTestRunner.class)
public class SearchTest {

    TestApplication application;
    MainActivity activity;

    @Before
    public void setup(){
        application = (TestApplication) RuntimeEnvironment.application;
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }
}
