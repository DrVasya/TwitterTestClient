package home.ilyinvasiliy.ru.twittertest;

import android.app.Application;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


public class TwitterApplication extends Application {
    private static final String TWITTER_KEY = "8dCTIgqNgOiZCWVMMvUhsTTN3";
    private static final String TWITTER_SECRET = "WkCKWgm5ebjMGt7B5vt4mwlemfA5r5Jrsbim52bS9Dor8KDJBf";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }
}
