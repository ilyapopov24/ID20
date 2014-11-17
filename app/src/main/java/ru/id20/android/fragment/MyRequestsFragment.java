package ru.id20.android.fragment;

/**
 * Created by Dre on 28.09.2014.
 */

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import ru.id20.android.MainMenuActivity;
import ru.id20.android.R;
import ru.id20.android.util.Globals;
import ru.id20.android.util.RequestListUpdateTask;


/**
 * Created by Dre on 26.09.2014.
 */
public class MyRequestsFragment extends Fragment implements View.OnClickListener
{
    private boolean indicator = false;
    private TextView number;
    private TextView dateAndTime;
    private TextView addressFrom;
    private TextView nameOfPassengers;
    private TextView addressTo;
    private TextView auto;
    private TextView driver;
    private TextView company;
    private TextView status;
    private TextView dateCreated;

    private ListView number_value, dateAndTime_value, addressFrom_value, nameOfPassengers_value,
            addressTo_value, auto_value, driver_value, company_value, status_value, dateCreated_value;
    private AsyncTask task;
    Context context;
    MyRequestsFragment myRequestsFragment;
    Globals globals;

    public static MyRequestsFragment newInstance(String fragmentName) {
        MyRequestsFragment fragment = new MyRequestsFragment();
        Bundle args = new Bundle();
        args.putString(MainMenuActivity.FRAGMENT_NAME, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }
    public MyRequestsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_list_temp, container, false);

        number = (TextView) fragmentView.findViewById(R.id.number);
        dateAndTime = (TextView) fragmentView.findViewById(R.id.dateAndTime);
        addressFrom = (TextView) fragmentView.findViewById(R.id.addressFrom);
        nameOfPassengers = (TextView) fragmentView.findViewById(R.id.nameOfPassengers);
        addressTo = (TextView) fragmentView.findViewById(R.id.addressTo);
        auto = (TextView) fragmentView.findViewById(R.id.auto);
        driver = (TextView) fragmentView.findViewById(R.id.driver);
        company = (TextView) fragmentView.findViewById(R.id.company);
        status = (TextView) fragmentView.findViewById(R.id.status);
        dateCreated = (TextView) fragmentView.findViewById(R.id.dateCreated);

        number_value = (ListView) fragmentView.findViewById(R.id.number_value);
        dateAndTime_value = (ListView) fragmentView.findViewById(R.id.dateAndTime_value);
        addressFrom_value = (ListView) fragmentView.findViewById(R.id.addressFrom_value);
        nameOfPassengers_value = (ListView) fragmentView.findViewById(R.id.nameOfPassengers_value);
        addressTo_value = (ListView) fragmentView.findViewById(R.id.addressTo_value);
        auto_value = (ListView) fragmentView.findViewById(R.id.auto_value);
        driver_value = (ListView) fragmentView.findViewById(R.id.driver_value);
        company_value = (ListView) fragmentView.findViewById(R.id.company_value);
        status_value = (ListView) fragmentView.findViewById(R.id.status_value);
        dateCreated_value = (ListView) fragmentView.findViewById(R.id.dateCreated_value);
        /*
        requestListView = (ListView) fragmentView.findViewById(R.id.requestListView);
        RequestsListAdapter adapter = new RequestsListAdapter(
                                                    getActivity().getBaseContext(),
                                                    new ArrayList<String>(Arrays.asList("1", "2", "3", "4")));
        requestListView.setAdapter(adapter);
        ListViewHelper.getListViewSize(requestListView); // вытягиваем ListView по высоте
        */

        number.setOnClickListener(this);
        dateAndTime.setOnClickListener(this);
        addressFrom.setOnClickListener(this);
        nameOfPassengers.setOnClickListener(this);
        addressTo.setOnClickListener(this);
        auto.setOnClickListener(this);
        driver.setOnClickListener(this);
        company.setOnClickListener(this);
        status.setOnClickListener(this);
        dateCreated.setOnClickListener(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        task = new RequestListUpdateTask(getActivity(), "access_token", prefs.getString("token", ""), "Claim_page", "1")
                .execute("https://id20.ru/api/claim/list/");
        /*
        number_value.setText(prefs.getString("number", ""));
        dateAndTime_value.setText(prefs.getString("date_time", ""));
        addressFrom_value.setText(prefs.getString("adress_from", ""));
        nameOfPassengers_value.setText(prefs.getString("passenger_fio", ""));
        addressTo_value.setText(prefs.getString("adress_to", ""));
        auto_value.setText(prefs.getString("car_name", ""));
        driver_value.setText(prefs.getString("driver_name", ""));
        company_value.setText(prefs.getString("company", ""));
        status_value.setText(prefs.getString("status", ""));
        dateCreated_value.setText(prefs.getString("created", ""));
        */
        globals = Globals.getInstance();
        while(!(prefs.getBoolean("indicator", false)))
        {
            setRequestList();

            try
            {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.number:
                setActivatedFalse();
                number.setActivated(true);

                break;

            case R.id.dateAndTime:
                setActivatedFalse();
                dateAndTime.setActivated(true);

                break;

            case R.id.addressFrom:
                setActivatedFalse();
                addressFrom.setActivated(true);

                break;

            case R.id.nameOfPassengers:
                setActivatedFalse();
                nameOfPassengers.setActivated(true);

                break;

            case R.id.addressTo:
                setActivatedFalse();
                addressTo.setActivated(true);

                break;
            case R.id.auto:
                setActivatedFalse();
                auto.setActivated(true);

                break;
            case R.id.driver:
                setActivatedFalse();
                driver.setActivated(true);

                break;
            case R.id.company:
                setActivatedFalse();
                company.setActivated(true);

                break;
            case R.id.status:
                setActivatedFalse();
                status.setActivated(true);

                break;
            case R.id.dateCreated:
                setActivatedFalse();
                dateCreated.setActivated(true);

                break;
        }
    }

    private void setActivatedFalse()
    {
        number.setActivated(false);
        dateAndTime.setActivated(false);
        addressFrom.setActivated(false);
        nameOfPassengers.setActivated(false);
        addressTo.setActivated(false);
        auto.setActivated(false);
        driver.setActivated(false);
        company.setActivated(false);
        status.setActivated(false);
        dateCreated.setActivated(false);
    }
    public void addInList()
    {

    }
    /*
    public void setNumber_value(String value)
    {
        number_value.setText(value);
    }
    public void setDateAndTime_value(String value)
    {
        dateAndTime_value.setText(value);
    }
    public void setAddressFrom_value(String value)
    {
        addressFrom_value.setText(value);
    }
    public void setNameOfPassengers_value(String value)
    {
        nameOfPassengers_value.setText(value);
    }
    public void setAddressTo_value(String value)
    {
        addressTo_value.setText(value);
    }
    public void setAuto_value(String value)
    {
        auto_value.setText(value);
    }
    public void setDriver_value(String value)
    {
        driver_value.setText(value);
    }
    public void setCompany_value(String value)
    {
        company_value.setText(value);
    }
    public void setStatus_value(String value)
    {
        status_value.setText(value);
    }
    public void setDateCreated_value(String value)
    {
        dateCreated_value.setText(value);
    }
    */
    private void setRequestList()
    {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getNumberArray());
        number_value.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getDate_timeArray());
        dateAndTime_value.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getAdress_fromArray());
        addressFrom_value.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getPassenger_fioArray());
        nameOfPassengers_value.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getAdress_toArray());
        addressTo_value.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getCar_nameArray());
        auto_value.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getDriver_nameArray());
        driver_value.setAdapter(adapter7);

        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getCompanyArray());
        company_value.setAdapter(adapter8);

        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getStatusArray());
        status_value.setAdapter(adapter9);

        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, globals.getCreatedArray());
        dateCreated_value.setAdapter(adapter10);
    }
}
