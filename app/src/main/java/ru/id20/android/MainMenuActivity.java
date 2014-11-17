package ru.id20.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;


public class MainMenuActivity extends Activity {

    public final static String FRAGMENT_NAME = "FRAGMENT_NAME";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        setContentView(R.layout.activity_main_menu);

        intent = new Intent(MainMenuActivity.this, AllActivity.class);
    }

    public void onClickRequests(View v) {
        //startActivity(new Intent(MainMenuActivity.this, CreateTaskActivity.class));
        intent.putExtra(FRAGMENT_NAME, "RequestsFragment");
        startActivity(intent);
    }

    public void onClickTasks(View v) {
        intent.putExtra(FRAGMENT_NAME, "TasksFragment");
        startActivity(intent);
    }

    public void onClickRouteLists(View v) {
        intent.putExtra(FRAGMENT_NAME, "RouteListsFragment");
        startActivity(intent);
    }

    public void onClickSettings(View v) {
        intent.putExtra(FRAGMENT_NAME, "SettingsFragment");
        startActivity(intent);
    }

}
