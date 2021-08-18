package com.example.hamavendor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.hamavendor.R;

public class Mobile_verification extends BaseActivity {
    ImageView back_reset;
    TextView timer, textView1, textView8;
    int time = 59;
    PinEntryEditText pinEntryEditText;
    Button button;
    View view;
    LinearLayout parent_enter_reset_pass;

    private String email;
    private ProgressBar pb_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_verification);
        back_reset = (ImageView) findViewById(R.id.back_reset);
        back_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
        timer = (TextView) findViewById(R.id.timer);
        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("0:" + checkDigit(time));
                time--;
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }


    public void reset(View view) {
        Intent i = new Intent(getApplicationContext(), Register.class);
        startActivity(i);
    }
}