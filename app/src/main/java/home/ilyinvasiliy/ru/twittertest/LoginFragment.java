package home.ilyinvasiliy.ru.twittertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginFragment extends Fragment {

    private TwitterLoginButton loginButton;
    private View mProgressContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        if(session != null && !session.getAuthToken().isExpired()) {
            getActivity().startActivity(new Intent(getActivity(), TimelineActivity.class));
            getActivity().finish();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, null);

        mProgressContainer = v.findViewById(R.id.login_progressContainer);
        mProgressContainer.setVisibility(View.INVISIBLE);

        loginButton = (TwitterLoginButton) v.findViewById(R.id.twitter_login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressContainer.setVisibility(View.VISIBLE);
            }
        });

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                getActivity().startActivity(new Intent(getActivity(), TimelineActivity.class));
                getActivity().finish();
            }

            @Override
            public void failure(TwitterException exception) {
                mProgressContainer.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), R.string.login_error, Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
