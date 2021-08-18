package com.example.hamavendor.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.hamavendor.R;
import com.example.hamavendor.adapter.OnBoardSliderAdapter;
import com.example.hamavendor.circleindicator.CustomCircleIndicator;

import java.util.List;

import static androidx.core.view.ViewCompat.animate;

public class OnBoardingActivity extends BaseActivity {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private int MCurentPage;
    Button button;
    TextView textView;
    CustomCircleIndicator indicator;
    OnBoardSliderAdapter sliderAdapter;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.well_come_screen);
        animate(linearLayout);
        constraintLayout = (ConstraintLayout) findViewById(R.id.container_of_home);
        textView = (TextView) findViewById(R.id.button2);
        indicator = (CustomCircleIndicator) findViewById(R.id.pageIndicatorView);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        sliderAdapter = new OnBoardSliderAdapter(getLayoutInflater(), this);
        viewPager.setAdapter(sliderAdapter);

        indicator.setViewPager(viewPager);



        ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                pageIndicatorView.setSelection(position);
//                pageIndicatorView.setAnimationType(AnimationType.WORM);
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        };
        viewPager.addOnPageChangeListener(viewListener);


    }


    public void animate(View view) {
        ConstraintLayout dialog = (ConstraintLayout) findViewById(R.id.lineranim);
        dialog.setVisibility(LinearLayout.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_home);
        animation.setDuration(2000);
        dialog.setAnimation(animation);
        dialog.animate();
        animation.start();
    }

//    public void onClick(View view) {
//        Intent intent = new Intent(getApplicationContext(),SignUp.class);
//        startActivity(intent);
//    }

    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }




    public void create_account(View view) {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
        finish();
    }


}
