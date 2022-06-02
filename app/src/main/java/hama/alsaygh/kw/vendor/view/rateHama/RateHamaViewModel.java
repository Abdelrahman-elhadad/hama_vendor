package hama.alsaygh.kw.vendor.view.rateHama;

import android.content.Context;
import android.view.View;
import android.widget.RatingBar;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;

public class RateHamaViewModel extends ViewModel {

    private final String TAG = "RecommendationViewModel";

    private final ProfileRepo authRepo;

    private float rating = 0;
    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    private MutableLiveData<GeneralResponse> loginResponseMutableLiveData;

    private LoginListener listener;

    public RateHamaViewModel(LoginListener listener) {
        this.listener = listener;
        authRepo = new ProfileRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public MutableLiveData<GeneralResponse> getObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }


    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }

    public void login(Context context) {
        authRepo.rateHama(context, rating, loginResponseMutableLiveData);

    }

    public void onResetClick(View view) {

        listener.validation();
    }


    public ObservableInt getLoginVisibility() {
        return loginVisibility;
    }

    public ObservableInt getPbLoginVisibility() {
        return pbLoginVisibility;
    }

    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        this.rating = rating;
    }

}
