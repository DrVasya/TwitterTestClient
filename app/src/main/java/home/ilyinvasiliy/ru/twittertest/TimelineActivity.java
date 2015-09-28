package home.ilyinvasiliy.ru.twittertest;

import android.support.v4.app.Fragment;

public class TimelineActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TimelineFragment();
    }
}
