package ru.id20.android.util;

/**
 * Created by hetfieldan24 on 12.10.2014.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hetfieldan24 on 12.09.2014.
 */
public class RequestListUpdateTask extends AsyncTask<String, Void, JSONObject>
{
    Context context;
    String par_name_1; String par_name_value_1;
    String par_name_2; String par_name_value_2;
    String result = "";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public RequestListUpdateTask(Context context, String par_name_1, String par_name_value_1,
                         String par_name_2, String par_name_value_2)
    {
        super();
        this.context = context;
        this.par_name_1 = par_name_1;
        this.par_name_value_1 = par_name_value_1;
        this.par_name_2 = par_name_2;
        this.par_name_value_2 = par_name_value_2;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... urls)
    {
     if(this.getStatus().equals(Status.FINISHED))
     {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();

        editor.putBoolean("indicator", true);

        editor.apply();
     }
     return loadJSON(urls[0]);
    }

    @Override
    protected void onPostExecute(JSONObject jsonData)
    {
        // если какой-то фейл, проверяем на null
        // фейл может быть по многим причинам: сервер сдох, нет сети на устройстве и т.д.
        if (jsonData != null)
        {
            super.onPostExecute(jsonData);
            try
            {
                // прочитать параметр, который отправил сервер;
                // здесь вместо "result" подставляйте то, что вам надо
                result = jsonData.getString("success");

                if(result.equals("true"))
                {
                    Toast.makeText(context, "Successful!", Toast.LENGTH_LONG).show();
                    JSONArray data = jsonData.getJSONArray("data");
                    String status, driver_name, number, car_name,
                            adress_to, created, company, passenger_fio, date_time, adress_from;
                    ArrayList<String> statusArray = new ArrayList<String>();
                    ArrayList<String> driver_nameArray = new ArrayList<String>();
                    ArrayList<String> numberArray = new ArrayList<String>();
                    ArrayList<String> car_nameArray = new ArrayList<String>();
                    ArrayList<String> adress_fromArray = new ArrayList<String>();
                    ArrayList<String> adress_toArray = new ArrayList<String>();
                    ArrayList<String> createdArray = new ArrayList<String>();
                    ArrayList<String> companyArray = new ArrayList<String>();
                    ArrayList<String> passenger_fioArray = new ArrayList<String>();
                    ArrayList<String> date_timeArray = new ArrayList<String>();

                    for(int i = 0; i < data.length(); i++)
                    {
                        JSONObject obj = data.getJSONObject(i);
                        status = obj.getString("status");
                        driver_name = obj.getString("driver_name");
                        adress_from = obj.getString("adress_from");
                        number = obj.getString("number");
                        car_name = obj.getString("car_name");
                        adress_to = obj.getString("adress_to");
                        created = obj.getString("created");
                        company = obj.getString("company");
                        passenger_fio = obj.getString("passenger_fio");
                        date_time = obj.getString("date_time");

                        statusArray.add(status);
                        driver_nameArray.add(driver_name);
                        numberArray.add(number);
                        car_nameArray.add(car_name);
                        adress_fromArray.add(adress_from);
                        adress_toArray.add(adress_to);
                        createdArray.add(created);
                        companyArray.add(company);
                        passenger_fioArray.add(passenger_fio);
                        date_timeArray.add(date_time);

                        Log.e("!!!DATA!!!", number);
                    }
                    Globals globals = Globals.getInstance();
                    globals.setStatusArray(statusArray);
                    globals.setAdress_fromArray(adress_fromArray);
                    globals.setAdress_toArray(adress_toArray);
                    globals.setCar_nameArray(car_nameArray);
                    globals.setCompanyArray(companyArray);
                    globals.setCreatedArray(createdArray);
                    globals.setDate_timeArray(date_timeArray);
                    globals.setNumberArray(numberArray);
                    globals.setDriver_nameArray(driver_nameArray);
                    globals.setPassenger_fioArray(passenger_fioArray);

                }
                else
                    Toast.makeText(context, "Not successful! ", Toast.LENGTH_LONG).show();

                editor.apply();

                // что-то делаем, к примеру вызываем метод главного Activity на обновление GUI
            }
            catch (JSONException e)
            {
                Toast.makeText(context, "Not successful! JSONException!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
    public JSONObject loadJSON(String url)
    {

        JSONParser jParser = new JSONParser();
        // здесь параметры необходимые в запрос добавляем
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(par_name_1, par_name_value_1));
        params.add(new BasicNameValuePair(par_name_2, par_name_value_2));
        // посылаем запрос методом GET
        JSONObject json = jParser.makeHttpRequest(url, "GET", params);

        return json;
    }

}