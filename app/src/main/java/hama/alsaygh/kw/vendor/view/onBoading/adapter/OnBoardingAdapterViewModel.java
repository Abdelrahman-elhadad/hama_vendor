package hama.alsaygh.kw.vendor.view.onBoading.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.onBoarding.OnBoard;
import hama.alsaygh.kw.vendor.view.auth.login.LoginActivity;
import hama.alsaygh.kw.vendor.view.auth.register.RegisterActivity;

public class OnBoardingAdapterViewModel extends ViewModel {

    private final String TAG = "OnBoardingViewModel";
    OnBoard onBoard;

    public OnBoardingAdapterViewModel(OnBoard onBoard) {
        this.onBoard = onBoard;
    }

    public String getTitle() {
        return onBoard.getTitle();
    }

    public String getDesc() {
        return onBoard.getShort_description();
    }


    public void animate(Context context, View view) {
        view.setVisibility(LinearLayout.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_home);
        animation.setDuration(2000);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    public void login(View view) {
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        view.getContext().startActivity(intent);
    }

    public void create_account(View view) {
        Intent intent = new Intent(view.getContext(), RegisterActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        view.getContext().startActivity(intent);
    }

}
