package hama.alsaygh.kw.vendor.view.main;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.widget.SeekBar;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.onBoarding.OnBoardResponse;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.view.home.HomeActivity;
import hama.alsaygh.kw.vendor.view.onBoading.OnBoardingActivity;

public class MainActivityViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";
    private MutableLiveData<OnBoardResponse> onBoardingResponseMutableLiveData;
    private final ObservableInt progress = new ObservableInt();

    public MainActivityViewModel() {
    }

    public MutableLiveData<OnBoardResponse> getOnBoardingObserver() {
        if (onBoardingResponseMutableLiveData == null)
            onBoardingResponseMutableLiveData = new MutableLiveData<>();

        return onBoardingResponseMutableLiveData;
    }

    public void getOnBoarding(Context context) {
        new GeneralRepo().getOnBoarding(context, onBoardingResponseMutableLiveData);
    }

    public void onSeekBarChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (progress == 100) {

            if (SharedPreferenceConstant.getSharedPreferenceUserToken(seekBar.getContext()).isEmpty()) {
                Intent intent = new Intent(seekBar.getContext(), OnBoardingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                seekBar.getContext().startActivity(intent);
            } else {
                Intent intent = new Intent(seekBar.getContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                seekBar.getContext().startActivity(intent);
            }

        }
    }

    public void startAnime(SeekBar seekBar) {
        final ValueAnimator anim = ValueAnimator.ofInt(0, seekBar.getMax());
        anim.setDuration(1500);
        anim.addUpdateListener(animation -> {
            progress.set((Integer) animation.getAnimatedValue());
        });
        anim.start();
    }

    public ObservableInt getProgress() {
        return progress;
    }
}
