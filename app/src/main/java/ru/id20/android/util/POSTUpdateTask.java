package ru.id20.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hetfieldan24 on 07.10.2014.
 */
public class POSTUpdateTask extends AsyncTask<String, Void, JSONObject>
{
    Context context;
    String par_name; String par_value;
    String response;
    SharedPreferences prefs;

    public POSTUpdateTask(Context context, String par_name, String par_value, String response)
    {
        super();
        this.context = context;
        this.par_name = par_name;
        this.par_value = par_value;
        this.response = response;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... urls)
    {
        return loadJSON(urls[0]);
    }

    public JSONObject loadJSON(String url)
    {

        JSONParser jParser = new JSONParser();
        // здесь параметры необходимые в запрос добавляем
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(par_name, par_value));

        params.add(new BasicNameValuePair("Claim[date_time]", prefs.getString("date_time", "")));
        params.add(new BasicNameValuePair("Claim[contact_name]", prefs.getString("contact_name", "")));
        params.add(new BasicNameValuePair("Claim[contact_phone]", prefs.getString("contact_phone", "")));
        params.add(new BasicNameValuePair("Claim[is_passenger]", prefs.getString("is_passenger", "")));
        params.add(new BasicNameValuePair("Claim[adress_from]", prefs.getString("adress_from", "")));
        params.add(new BasicNameValuePair("Claim[is_point]", prefs.getString("is_point", "")));
        params.add(new BasicNameValuePair("Claim[adress_to]", prefs.getString("adress_to", "")));
        params.add(new BasicNameValuePair("Claim[purposes]", prefs.getString("purposes", "")));
        params.add(new BasicNameValuePair("Claim[procuration]", prefs.getString("procuration", "")));
        params.add(new BasicNameValuePair("Claim[permit]", prefs.getString("permit", "")));
        // посылаем запрос методом POST
        JSONObject json = jParser.makeHttpRequest(url, "POST", params);

        return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonData)
    {
        // если какой-то фейл, проверяем на null
        // фейл может быть по многим причинам: сервер сдох, нет сети на устройстве и т.д.
        if (jsonData != null)
        {
            super.onPostExecute(jsonData);
            String res = "";
            String id;
            JSONObject obj = null;
            try
            {
                // прочитать параметр, который отправил сервер;
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = prefs.edit();
                res = jsonData.getString(response);
                id = res.substring(6, res.length() - 1).replace("\"", "");
                editor.putString("id", id);
                editor.apply();
            }
            catch (JSONException e)
            {
                Toast.makeText(context, "Не получилось... " + res, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(context, "Не получилось... ", Toast.LENGTH_LONG).show();
        }
    }
}