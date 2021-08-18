package com.example.hamavendor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hamavendor.R;

public class Register extends BaseActivity {

    ImageView img_back_register;
    TextView sign_in_register, wel_come_reg, sign_up_tocontinue, textView5;
    EditText edtFirstName, edtLastName, edtEmail, edtPassword;
    Button btnSign;
    LinearLayout linearLayout;
    ProgressBar pbSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        img_back_register = (ImageView) findViewById(R.id.img_back_register);
        img_back_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
        sign_in_register = (TextView) findViewById(R.id.sign_in_register);
        sign_in_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);

            }
        });
        wel_come_reg = (TextView) findViewById(R.id.wel_come_register);
        sign_up_tocontinue = (TextView) findViewById(R.id.sign_up_tocontinue);
        edtFirstName = (EditText) findViewById(R.id.edt_first_name);
        edtLastName = (EditText) findViewById(R.id.edt_last_name);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_first_name);
        textView5 = (TextView) findViewById(R.id.textView5);
        btnSign = (Button) findViewById(R.id.btn_sign);
        linearLayout = (LinearLayout) findViewById(R.id.parent_register);
        pbSign = findViewById(R.id.pb_sign);
    }

    public void nextRegister(View view) {
        Intent i = new Intent(getApplicationContext(), Mobile_verification.class);
        startActivity(i);


    }

    public void login(View view) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}
