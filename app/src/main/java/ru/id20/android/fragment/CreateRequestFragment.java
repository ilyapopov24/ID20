package ru.id20.android.fragment;

/**
 * Created by Hetfieldan24 on 02.10.2014.
 */

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import ru.id20.android.MainMenuActivity;
import ru.id20.android.MyDatePickerActivity;
import ru.id20.android.R;
import ru.id20.android.TestGoogleActivity;
import ru.id20.android.util.MaskedWatcher;
import ru.id20.android.util.POSTUpdateTask;
import ru.id20.android.view.CustomButton;
import ru.id20.android.view.CustomEditText;


/**
 * Created by Dre on 26.09.2014.
 */
public class CreateRequestFragment extends Fragment implements View.OnFocusChangeListener, View.OnClickListener {

    CustomEditText date;
    CustomEditText time;
    CustomEditText secondName;
    CustomEditText name;
    CustomEditText patronymic;
    CustomEditText contactInfo;
    CustomEditText addressFrom;
    CustomEditText intermediatePoints;
    CustomEditText additionalInstructions1;
    CustomEditText addressTo;
    CustomEditText additionalInstructions2;
    CustomEditText purpose;
    CustomButton createTaskButton;
    RadioButton passengerRadioButtonYes,
                procuratoryRadioButtonYes,
                permitRadioButtonYes;
    SharedPreferences prefs;
    String date_shared, time_shared;
    View fragmentView;
    String token;
    private AsyncTask POSTUpdateTask;

    public static CreateRequestFragment newInstance(String fragmentName) {
        CreateRequestFragment fragment = new CreateRequestFragment();
        Bundle args = new Bundle();
        args.putString(MainMenuActivity.FRAGMENT_NAME, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    public CreateRequestFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        fragmentView = inflater.inflate(R.layout.fragment_create_request, container, false);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        date_shared = prefs.getString("date_month_year", "");
        time_shared = prefs.getString("time", "");
        createTaskButton = (CustomButton)fragmentView.findViewById(R.id.createTaskButton);
        date = (CustomEditText)fragmentView.findViewById(R.id.date);
        time = (CustomEditText)fragmentView.findViewById(R.id.time);
        secondName = (CustomEditText)fragmentView.findViewById(R.id.secondName);
        name = (CustomEditText)fragmentView.findViewById(R.id.name);
        patronymic = (CustomEditText)fragmentView.findViewById(R.id.patronymic);
        contactInfo = (CustomEditText)fragmentView.findViewById(R.id.contactInfo);
        addressFrom = (CustomEditText)fragmentView.findViewById(R.id.addressFrom);
        intermediatePoints = (CustomEditText)fragmentView.findViewById(R.id.intermediatePoints);
        additionalInstructions1 = (CustomEditText)fragmentView.findViewById(R.id.additionalInstructions1);
        addressTo = (CustomEditText)fragmentView.findViewById(R.id.addressTo);
        additionalInstructions2 = (CustomEditText)fragmentView.findViewById(R.id.additionalInstructions2);
        purpose = (CustomEditText)fragmentView.findViewById(R.id.purpose);
        passengerRadioButtonYes = (RadioButton)fragmentView.findViewById(R.id.passengerRadioButtonYes);
        procuratoryRadioButtonYes = (RadioButton)fragmentView.findViewById(R.id.procuratoryRadioButtonYes);
        permitRadioButtonYes = (RadioButton)fragmentView.findViewById(R.id.permitRadioButtonYes);


        date.setRawInputType(0x00000000);
        time.setRawInputType(0x00000000);
        secondName.setRawInputType(0x00000000);
        name.setRawInputType(0x00000000);
        patronymic.setRawInputType(0x00000000);
        contactInfo.setRawInputType(0x00000000);
        addressFrom.setRawInputType(0x00000000);
        intermediatePoints.setRawInputType(0x00000000);
        additionalInstructions1.setRawInputType(0x00000000);
        addressTo.setRawInputType(0x00000000);
        additionalInstructions2.setRawInputType(0x00000000);
        purpose.setRawInputType(0x00000000);

        date.setOnFocusChangeListener(this);
        time.setOnFocusChangeListener(this);
        secondName.setOnFocusChangeListener(this);
        name.setOnFocusChangeListener(this);
        patronymic.setOnFocusChangeListener(this);
        contactInfo.setOnFocusChangeListener(this);
        addressFrom.setOnFocusChangeListener(this);
        intermediatePoints.setOnFocusChangeListener(this);
        additionalInstructions1.setOnFocusChangeListener(this);
        addressTo.setOnFocusChangeListener(this);
        additionalInstructions2.setOnFocusChangeListener(this);
        purpose.setOnFocusChangeListener(this);
        createTaskButton.setOnClickListener(this);

        return fragmentView;
    }

    int translateIdToIndex(int id)
    {
        int index = -1;
        switch (id)
        {
            case R.id.date:
                index = 1;
                break;
            case R.id.time:
                index = 2;
                break;
            case R.id.secondName:
                index = 3;
                break;
            case R.id.name:
                index = 4;
                break;
            case R.id.patronymic:
                index = 5;
                break;
            case R.id.contactInfo:
                index = 6;
                break;
            case R.id.addressFrom:
                index = 7;
                break;
            case R.id.intermediatePoints:
                index = 8;
                break;
            case R.id.additionalInstructions1:
                index = 9;
                break;
            case R.id.addressTo:
                index = 10;
                break;
            case R.id.additionalInstructions2:
                index = 11;
                break;
            case R.id.purpose:
                index = 12;
                break;
        }
        return index;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        if (hasFocus)
        {
            int buttonIndex = translateIdToIndex(v.getId());
            if(buttonIndex == 1 || buttonIndex == 2)
                startActivity(new Intent(getActivity(), MyDatePickerActivity.class));
            else if(buttonIndex == 6)
                contactInfo.addTextChangedListener(new MaskedWatcher("(###) ###-##-##"));
            else if(buttonIndex == 7 || buttonIndex == 8 || buttonIndex == 10)
            {
                startActivity(new Intent(getActivity(), TestGoogleActivity.class));
            }
            else if(buttonIndex >=1 && buttonIndex <= 12)
            {
                final CustomEditText customEditText = (CustomEditText)v;
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                final CustomEditText input = new CustomEditText(getActivity());
                input.setHint(customEditText.getHint());
                input.addTextChangedListener(new MaskedWatcher("(###) ###-##-##"));
                alert.setView(input);

                alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        customEditText.setText(input.getText().toString());
                    }
                });

                alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                customEditText.clearFocus();
                alert.show();
            }
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        date.clearFocus();
        time.clearFocus();
        secondName.clearFocus();
        name.clearFocus();
        patronymic.clearFocus();
        contactInfo.clearFocus();
        addressFrom.clearFocus();
        intermediatePoints.clearFocus();
        additionalInstructions1.clearFocus();
        addressTo.clearFocus();
        additionalInstructions2.clearFocus();
        purpose.clearFocus();

        date_shared = prefs.getString("date_month_year", "");
        time_shared = prefs.getString("time", "");

        date.setText(date_shared);
        time.setText(time_shared);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("date_month_year");
        editor.remove("time");

        editor.apply();
    }
    @Override
    public void onClick(View v)
    {
        if(addressFrom.getText().toString().equals("") || addressTo.getText().toString().equals(""))
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String token = prefs.getString("token", "");
            String is_passenger = "0";
            String is_procuratory = "0";
            String is_permit = "0";

            if (passengerRadioButtonYes.isChecked())
                is_passenger = "1";
            if (procuratoryRadioButtonYes.isChecked())
                is_procuratory = "1";
            if (permitRadioButtonYes.isChecked())
                is_permit = "1";

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("date_time", date.getText().toString() + " " + time.getText().toString());
            editor.putString("contact_name", secondName.getText().toString() + " " + name.getText().toString() + " " +
                    patronymic.getText().toString());
            editor.putString("contact_phone", contactInfo.getText().toString());
            editor.putString("is_passenger", is_passenger);
            editor.putString("adress_from", addressFrom.getText().toString());
            editor.putString("is_point", "0");                                       //ИСПРАВИТЬ КОГДА СДЕЛАЕМ ВЫБОР ТОЧЕК С КАРТЫ!!!
            editor.putString("adress_to", addressTo.getText().toString());
            editor.putString("purposes", purpose.getText().toString());
            editor.putString("procuration", is_procuratory);
            editor.putString("permit", is_permit);

            editor.apply();

            POSTUpdateTask = new POSTUpdateTask(getActivity(), "access_token", token, "data")
                    .execute("https://id20.ru/api/claim/create/");
        }
    }
}