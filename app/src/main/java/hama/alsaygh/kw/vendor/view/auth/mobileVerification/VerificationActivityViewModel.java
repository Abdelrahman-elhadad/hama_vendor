package hama.alsaygh.kw.vendor.view.auth.mobileVerification;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.model.user.LoginResponse;
import hama.alsaygh.kw.vendor.repo.AuthRepo;

public class VerificationActivityViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";
    int time = 59;
    private final AuthRepo authRepo;
    private String userName, email;
    private TextView timer;

    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    private final LoginListener listener;
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData;
    private MutableLiveData<GeneralResponse> resendResponseMutableLiveData;

    public VerificationActivityViewModel(LoginListener listener) {
        this.listener = listener;
        authRepo = new AuthRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTimer(TextView timer) {
        this.timer = timer;
    }

    public TextView getTimer() {
        return timer;
    }

    public MutableLiveData<LoginResponse> getVerifyObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }

    public MutableLiveData<GeneralResponse> getResendObservable() {
        if (resendResponseMutableLiveData == null)
            resendResponseMutableLiveData = new MutableLiveData<>();

        return resendResponseMutableLiveData;
    }

    public String getUserName() {
        return userName;
    }


    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }

    public void login(Context context) {
        authRepo.verifyCode(context, email, userName, loginResponseMutableLiveData);

    }


    public void onResetClick(View view) {
        if (MainApplication.isConnected) {
            if (listener != null)
                listener.validation();
            if (userName != null && !userName.isEmpty()) {
                setLoginVisibility(View.GONE);
                setPbLoginVisibility(View.VISIBLE);

                login(view.getContext());
            }
        } else
            Snackbar.make(view, view.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

    }

    public void onResendClick(View view) {
        if (MainApplication.isConnected) {
            authRepo.forgetPassword(view.getContext(), email, resendResponseMutableLiveData);
            startTimer(timer, (TextView) view);
        } else
            Snackbar.make(view, view.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

    }

    public TextWatcher userNameTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userName = s.toString();
            }
        };
    }

    public ObservableInt getLoginVisibility() {
        return loginVisibility;
    }

    public ObservableInt getPbLoginVisibility() {
        return pbLoginVisibility;
    }

    public void startTimer(TextView timer, TextView resend) {
        time = 59;

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("0:" + checkDigit(time));
                time--;
                if (time == 0) {
                    resend.setTextColor(ContextCompat.getColor(resend.getContext(), R.color.dgdg));
                    cancel();
                } else
                    resend.setTextColor(ContextCompat.getColor(resend.getContext(), R.color.active_code));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}
