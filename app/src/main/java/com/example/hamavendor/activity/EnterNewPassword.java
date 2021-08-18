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

public class EnterNewPassword extends BaseActivity {
    ImageView back_enter_new_password;
    TextView sign_in_enter_new_password, textView, textView1, textView3;
    Button confirm;
    LinearLayout liner_enter_new_password;
    EditText editText2, editText22;
    ProgressBar pb_confirm;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_new_password);

        back_enter_new_password = findViewById(R.id.back_enter_new_password);
        back_enter_new_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Mobile_verification.class);
                startActivity(i);
            }
        });


    }

    public void Confirm(View view) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}