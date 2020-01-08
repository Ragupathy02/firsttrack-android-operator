package com.firstpage.user;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public TextInputEditText et_mail;
    public TextInputEditText et_password;
    public AppCompatButton bt_signin;
    String regEx =
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        et_mail=(TextInputEditText)findViewById(R.id.et_email);
        et_password=(TextInputEditText)findViewById(R.id.et_password);
        bt_signin=(AppCompatButton)findViewById(R.id.btn_signin);
        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checksigin())
                {
                    Toast.makeText(getApplicationContext(),"sigin",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean checksigin()
    {
        Matcher matcherObj = Pattern.compile(regEx).matcher(et_mail.getText().toString());
        if (et_mail.getText().toString().length() == 0) {
            et_mail.requestFocus();
            et_mail.setError("Enter your email id");
            return false;
        } else if (!matcherObj.matches()) {
            et_mail.requestFocus();
            et_mail.setError("Enter valid email id");
            return false;
        } else if (et_password.length() == 0) {
            et_password.requestFocus();
            et_password.setError("Enter your password");
            return false;
        }
        return true;

    }
}
