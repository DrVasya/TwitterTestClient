package home.ilyinvasiliy.ru.twittertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public abstract class ToolbarFragment extends Fragment {

    private Toolbar mTopToolbar;
    private Toolbar mBottomToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTopToolbar = (Toolbar)  getActivity().findViewById(R.id.toolbar_top);
        if(mTopToolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(mTopToolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        }

        mBottomToolbar = (Toolbar)  getActivity().findViewById(R.id.toolbar_bottom);
        if(mBottomToolbar != null) {
            mBottomToolbar.inflateMenu(R.menu.menu_main);
            setupEvenlyDistributedToolbar(mBottomToolbar);

            mBottomToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return processOptionsItemClick(item);
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(mBottomToolbar == null) {
            inflater.inflate(R.menu.menu_main, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return processOptionsItemClick(item) || super.onOptionsItemSelected(item);
    }

    private boolean processOptionsItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(getActivity(), EmptyActivity.class));
                return true;
            default:
                return false;
        }
    }
    protected Toolbar getTopToolbar() {
        return mTopToolbar;
    }

    private void setupEvenlyDistributedToolbar(Toolbar toolbar){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        toolbar.setContentInsetsAbsolute(10, 10);

        int childCount = toolbar.getChildCount();
        int screenWidth = metrics.widthPixels;

        Toolbar.LayoutParams toolbarParams = new Toolbar.LayoutParams(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int i = 0; i < childCount; i++){
            View childView = toolbar.getChildAt(i);

            if(childView instanceof ViewGroup){
                childView.setLayoutParams(toolbarParams);
                int innerChildCount = ((ViewGroup) childView).getChildCount();
                int itemWidth  = (screenWidth / innerChildCount);
                ActionMenuView.LayoutParams params = new ActionMenuView.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

                for(int j = 0; j < innerChildCount; j++){
                    View grandChild = ((ViewGroup) childView).getChildAt(j);
                    if(grandChild instanceof ActionMenuItemView){
                        grandChild.setLayoutParams(params);
                    }
                }
            }
        }
    }
}
