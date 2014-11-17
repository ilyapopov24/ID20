package ru.id20.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import ru.id20.android.util.ApplicationsDatabaseHelper;


public class CreateTaskActivity extends Activity {

    EditText editText, editText1, editText2, editText3, editText4,
            editText5, editText6, editText7, editText8, editText9, editText10, editText11;

    ApplicationsDatabaseHelper adb;
    RadioButton rbYes, rbNo;
    TextView tv21;
    String login;
    String password;
    String token;
    String date_month_year;
    String time;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        login = prefs.getString("login", "no_login");
        password = prefs.getString("password", "no_password");
        token = prefs.getString("token", "no_token");
        date_month_year = prefs.getString("date_month_year", "");
        time = prefs.getString("time", "");

        editText = (EditText) findViewById(R.id.textView);
        editText1 = (EditText) findViewById(R.id.textView1);
        editText2 = (EditText) findViewById(R.id.textView2);
        editText3 = (EditText) findViewById(R.id.textView3);
        editText4 = (EditText) findViewById(R.id.textView4);
        editText5 = (EditText) findViewById(R.id.textView5);
        editText6 = (EditText) findViewById(R.id.textView6);
        editText7 = (EditText) findViewById(R.id.textView7);
        editText8 = (EditText) findViewById(R.id.textView8);
        editText9 = (EditText) findViewById(R.id.textView9);
        editText10 = (EditText) findViewById(R.id.textView10);
        editText11 = (EditText) findViewById(R.id.textView11);

        editText.setRawInputType(0x00000000);
        editText1.setRawInputType(0x00000000);

        tv21 = (TextView) findViewById(R.id.textView21);
        rbYes = (RadioButton) findViewById(R.id.passengerRadioButton1);
        rbNo = (RadioButton) findViewById(R.id.passengerRadioButton2);
        adb = new ApplicationsDatabaseHelper(this);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    startActivity(new Intent(CreateTaskActivity.this, MyDatePickerActivity.class));
                }
            }
        });
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    startActivity(new Intent(CreateTaskActivity.this, MyDatePickerActivity.class));
                }
            }
        });
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText2.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText2.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText3.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText3.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText4.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText4.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText5.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText5.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText6.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText6.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText7.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText7.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText8.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText8.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText9.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText9.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText9.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText10.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText10.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
        editText11.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateTaskActivity.this);

                    final EditText input = new EditText(CreateTaskActivity.this);
                    input.setHint(editText11.getHint());
                    alert.setView(input);

                    alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            editText11.setText(input.getText().toString());
                        }
                    });

                    alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();
                }
            }
        });
    }

    public void createTask(View v) {
        String isPassenger = "";

        if (rbNo.isChecked())
            isPassenger = "No";
        else if (rbYes.isChecked())
            isPassenger = "Yes";
        adb.addContact(new ApplicationsContact(login, password, token, editText5.getText().toString(),
                date_month_year + "; " + time,
                editText6.getText().toString(), editText9.getText().toString(),
                editText2.getText().toString() + " " + editText3.getText().toString() + " " + editText4.getText().toString(),
                editText5.getText().toString(), editText11.getText().toString(),
                editText8.getText().toString() + "; " + editText10.getText().toString(),
                isPassenger, editText7.getText().toString(), "Yes", "Yes", "Yes"));

    }

    @Override
    public void onResume()
    {
        super.onResume();
        editText = (EditText) findViewById(R.id.textView);
        editText1 = (EditText) findViewById(R.id.textView1);
        editText.setText(date_month_year);
        editText1.setText(time);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CreateTaskActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("date_month_year");
        editor.remove("time");

        date_month_year = prefs.getString("date_month_year", "");
        time = prefs.getString("time", "");

        editor.commit();
    }

    public void onClickBack(View v) {
        finish();
    }

}


