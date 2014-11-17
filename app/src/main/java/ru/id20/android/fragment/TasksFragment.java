package ru.id20.android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.id20.android.MainMenuActivity;
import ru.id20.android.R;

/**
 * Created by Dre on 26.09.2014.
 */
public class TasksFragment extends Fragment {

    public static TasksFragment newInstance(String fragmentName) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putString(MainMenuActivity.FRAGMENT_NAME, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    public TasksFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_tasks, container, false);

        return fragmentView;
    }

}
