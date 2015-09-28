package home.ilyinvasiliy.ru.twittertest;

import android.support.v4.app.Fragment;

public class EmptyActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new EmptyFragment();
    }
}
