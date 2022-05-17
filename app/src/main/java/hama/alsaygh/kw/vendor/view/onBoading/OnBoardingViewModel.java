package hama.alsaygh.kw.vendor.view.onBoading;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.onBoarding.OnBoard;
import hama.alsaygh.kw.vendor.model.onBoarding.OnBoardResponse;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.view.auth.login.LoginActivity;
import hama.alsaygh.kw.vendor.view.auth.register.RegisterActivity;

public class OnBoardingViewModel extends ViewModel {

    private final String TAG = "OnBoardingViewModel";
    private MutableLiveData<OnBoardResponse> onBoardingResponseMutableLiveData;

    public OnBoardingViewModel() {
    }

    public MutableLiveData<OnBoardResponse> getOnBoardingObserver() {
        if (onBoardingResponseMutableLiveData == null)
            onBoardingResponseMutableLiveData = new MutableLiveData<>();

        return onBoardingResponseMutableLiveData;
    }


    public void getOnBoarding(Context context) {

        List<OnBoard> onBoards = SharedPreferenceConstant.getSharedPreferenceOnBoarding(context);
        if (onBoards != null && !onBoards.isEmpty()) {

            OnBoardResponse onBoardResponse = new OnBoardResponse();
            onBoardResponse.setStatus(true);
            onBoardResponse.setData(onBoards);
            onBoardingResponseMutableLiveData.setValue(onBoardResponse);
        } else
            new GeneralRepo().getOnBoarding(context, onBoardingResponseMutableLiveData);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        view.getContext().startActivity(intent);
    }

}
