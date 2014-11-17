package ru.id20.android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ru.id20.android.AllActivity;
import ru.id20.android.MainMenuActivity;
import ru.id20.android.R;

/**
 * Created by Dre on 26.09.2014.
 */
public class RequestsFragment extends Fragment implements View.OnClickListener {

    private LinearLayout createRequestLinearLayout;
    private LinearLayout myRequestsLinearLayout;
    private LinearLayout profileLinearLayout;

    public static RequestsFragment newInstance(String fragmentName) {
        RequestsFragment fragment = new RequestsFragment();
        Bundle args = new Bundle();
        args.putString(MainMenuActivity.FRAGMENT_NAME, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    public RequestsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_requests, container, false);

        createRequestLinearLayout = (LinearLayout) fragmentView.findViewById(R.id.createRequestLinearLayout);
        myRequestsLinearLayout = (LinearLayout) fragmentView.findViewById(R.id.myRequestsLinearLayout);
        profileLinearLayout = (LinearLayout) fragmentView.findViewById(R.id.profileLinearLayout);

        createRequestLinearLayout.setOnClickListener(this);
        myRequestsLinearLayout.setOnClickListener(this);
        profileLinearLayout.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createRequestLinearLayout:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment, CreateRequestFragment.newInstance("CreateRequestFragment"))
                        .addToBackStack(null)
                .commit();
                break;

            case R.id.myRequestsLinearLayout:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment, MyRequestsFragment.newInstance("MyRequestsFragment"))
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.profileLinearLayout:
                ((AllActivity) getActivity()).onClickSettings(profileLinearLayout);
                break;
        }
    }
}
