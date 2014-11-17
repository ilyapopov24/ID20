package ru.id20.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import ru.id20.android.util.DatabaseHelper;


public class RegisterActivity extends Activity
{
    public static final String TAG = "Register";

    TextView tvInfo;
    DatabaseHelper db;
    StringBuilder sb = new StringBuilder();
    EditText name, password, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvInfo = (TextView) findViewById(R.id.info);
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);

        db = new DatabaseHelper(this);


    }
    public void finishClick(View v)
    {
        startActivity(new Intent(RegisterActivity.this, EnterActivity.class));
    }
    public void getInfoClick(View v)
    {
        // Считываем все контакты
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts)
        {
            String allContacts = "ID: " + cn.getID() + ", Имя: "
                    + cn.getName() + ", Пароль: " + cn.getPassword() + ", E-mail: " + cn.getEmail() + ", Номер: "
                    + cn.getPhoneNumber() + "\n";

            sb.append(allContacts);
        }

        tvInfo.setText(sb.toString());
    }
    public void createUser(View v)
    {

    }

}
