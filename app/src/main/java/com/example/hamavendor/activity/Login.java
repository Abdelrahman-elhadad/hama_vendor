package com.example.hamavendor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hamavendor.R;

public class Login extends BaseActivity {
    Switch aSwitch;
    Button btnLogin;
    TextView forget, wellcome, signin, use, dont, signup;
    ImageView img_back;
    EditText user_name, edit_password;
    LinearLayout parent;
    ProgressBar pbLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        forget = (TextView) findViewById(R.id.forget);
        edit_password = (EditText) findViewById(R.id.edit_password);
        signin = (TextView) findViewById(R.id.sign_in);

        signup = (TextView) findViewById(R.id.signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        user_name = (EditText) findViewById(R.id.editText);
        pbLogin = findViewById(R.id.pb_login);
        parent = (LinearLayout) findViewById(R.id.liner1);

        wellcome = (TextView) findViewById(R.id.well_come);

        img_back = (ImageView) findViewById(R.id.img_back_login);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), OnBoardingActivity.class);
                startActivity(i);

            }
        });


    }

    public void forgetPassword(View view) {
        Intent i = new Intent(getApplicationContext(), Mobile_verification.class);
        startActivity(i);
    }

    public void SignUpNow(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
        startActivity(i);
    }
}
