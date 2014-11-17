package ru.id20.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import ru.id20.android.util.GETUpdateTask;

public class EnterActivity extends Activity {

    private EditText passwordEditText, emailEditText;
    private AsyncTask updateTask;
    /*
    public static final String TAG = "MainMenu";
    private TextView tv, info, names, values;
    private String str = "";
    private String wishes;
    private String is_passenger;
    private String permit;
    private String purposes;
    private String contact_phone;
    private String number;
    private String id;
    private String adress_to;
    private String created;
    private String is_point;
    private String contact_name;
    private String date_time;
    private String procuration;
    private String adress_from;
    private String success;
    private String str = "\"wishes\":\"\",\"is_passenger\":\"0\",\"permit\":\"1\"";
    */


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        emailEditText = (EditText)findViewById(R.id.emailEditText);

        /*
        obj = null;

        passwordEditText.setTransformationMethod(null);
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(hasFocus)
                {
                    if(passwordEditText.getText().toString().equals("Пароль"))
                        passwordEditText.setText("");
                    passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                }
                else
                {
                    if (passwordEditText.getText().toString().equals(""))
                    {
                        passwordEditText.setTransformationMethod(null);
                        passwordEditText.setText("Пароль");

                    }
                }
            }
        });
        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(hasFocus)
                {
                    if(emailEditText.getText().toString().equals("Логин"))
                        emailEditText.setText("");
                }
                else if(emailEditText.getText().toString().equals(""))
                    emailEditText.setText("Логин");
            }
        });
    */


    }

    public void onClickLogin(View v)
    {
        final String mEmail = emailEditText.getText().toString();
        final String mPassword = passwordEditText.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        updateTask = new GETUpdateTask(this, "login", mEmail, "password", mPassword)
                .execute("https://id20.ru/api/auth/login/");

        editor.putString("login", emailEditText.getText().toString());
        editor.putString("password", passwordEditText.getText().toString());
        editor.apply();
    }

    public void onClickReg(View v)
    {
        //startActivity(new Intent(MainMenu.this, Register.class));
    }

    public void startMyActivity()
    {
        startActivity(new Intent(EnterActivity.this, MainMenuActivity.class));
        finish();
    }
}
