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
public class SettingsFragment extends Fragment {

    public static SettingsFragment newInstance(String fragmentName) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(MainMenuActivity.FRAGMENT_NAME, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_settings, container, false);

        //plasmaSpinner = (Spinner) fragmentView.findViewById(R.id.plasmaSpinner);

        return fragmentView;
    }

}
