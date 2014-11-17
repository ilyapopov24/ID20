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
import java.util.List;

import ru.id20.android.EnterActivity;

/**
 * Created by hetfieldan24 on 12.09.2014.
 */
public class GETUpdateTask extends AsyncTask<String, Void, JSONObject>
{
    String cnt = "0";
    Context context;
    String par_name_1; String par_name_value_1;
    String par_name_2; String par_name_value_2;
    String result = "";
    public GETUpdateTask(Context context, String par_name_1, String par_name_value_1,
                         String par_name_2, String par_name_value_2)
    {
        super();
        this.context = context;
        this.par_name_1 = par_name_1;
        this.par_name_value_1 = par_name_value_1;
        this.par_name_2 = par_name_2;
        this.par_name_value_2 = par_name_value_2;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... urls) {
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

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = prefs.edit();
                String data;
                String token;

                if(result.equals("true"))
                {
                    Toast.makeText(context, "Log-in is successful!: " + result, Toast.LENGTH_LONG).show();
                    data = jsonData.getString("data");
                    token = data.substring(17, data.length() - 2);
                    editor.putString("token", token);
                    ((EnterActivity)context).startMyActivity();
                }
                else
                    Toast.makeText(context, "Log-in is not successful! Success: " + result, Toast.LENGTH_LONG).show();

                editor.apply();

                // что-то делаем, к примеру вызываем метод главного Activity на обновление GUI
            }
            catch (JSONException e)
            {
                Toast.makeText(context, "Log-in is not successful! Success: " + result, Toast.LENGTH_LONG).show();
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