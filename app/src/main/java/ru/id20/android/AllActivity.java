package ru.id20.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ru.id20.android.fragment.RequestsFragment;
import ru.id20.android.fragment.RouteListsFragment;
import ru.id20.android.fragment.SettingsFragment;
import ru.id20.android.fragment.TasksFragment;

/**
 * Created by Dre on 26.09.2014.
 */
public class AllActivity extends Activity {

    private Fragment fragment;
    private ImageView requestsImageView;
    private ImageView tasksImageView;
    private ImageView routeListsImageView;
    private ImageView settingsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        setContentView(R.layout.activity_all);

        initUi();
    }

    private void initUi() {
        requestsImageView = (ImageView) findViewById(R.id.requestsImageView);
        tasksImageView = (ImageView) findViewById(R.id.tasksImageView);
        routeListsImageView = (ImageView) findViewById(R.id.routeListsImageView);
        settingsImageView = (ImageView) findViewById(R.id.settingsImageView);

        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra(MainMenuActivity.FRAGMENT_NAME);

        if("RequestsFragment".equals(fragmentName)) {
            fragment = RequestsFragment.newInstance(fragmentName);
            requestsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_1_green));
        }
        else if("TasksFragment".equals(fragmentName)) {
            fragment = TasksFragment.newInstance(fragmentName);
            tasksImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_2_green));
        }
        else if("RouteListsFragment".equals(fragmentName)) {
            fragment = RouteListsFragment.newInstance(fragmentName);
            routeListsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_3_green));
        }
        else if("SettingsFragment".equals(fragmentName)) {
            fragment = SettingsFragment.newInstance(fragmentName);
            settingsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_4_green));
        }

        getFragmentManager().beginTransaction()
            .replace(R.id.fragmentFrameLayout, fragment)
        .commit();
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickRequests(View v) {
        requestsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_1_green));
        tasksImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_2));
        routeListsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_3));
        settingsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_4));
        getFragmentManager().beginTransaction()
            .replace(R.id.fragmentFrameLayout, RequestsFragment.newInstance("RequestsFragment"))
        .commit();
    }

    public void onClickTasks(View v) {
        requestsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_1));
        tasksImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_2_green));
        routeListsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_3));
        settingsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_4));
        getFragmentManager().beginTransaction()
            .replace(R.id.fragmentFrameLayout, TasksFragment.newInstance("TasksFragment"))
        .commit();
    }

    public void onClickRouteLists(View v) {
        requestsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_1));
        tasksImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_2));
        routeListsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_3_green));
        settingsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_4));
        getFragmentManager().beginTransaction()
            .replace(R.id.fragmentFrameLayout, RouteListsFragment.newInstance("RouteListsFragment"))
        .commit();
    }

    public void onClickSettings(View v) {
        requestsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_1));
        tasksImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_2));
        routeListsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_3));
        settingsImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottom_bar_button_4_green));
        getFragmentManager().beginTransaction()
            .replace(R.id.fragmentFrameLayout, SettingsFragment.newInstance("SettingsFragment"))
        .commit();
    }

}