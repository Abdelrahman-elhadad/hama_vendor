package hama.alsaygh.kw.vendor.view.auth.resetPassword;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.repo.AuthRepo;
import hama.alsaygh.kw.vendor.view.auth.register.RegisterActivity;

public class EnterNewPasswordViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";

    private final AuthRepo authRepo;
    private String password, confirmPassword, token;

    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    private final LoginListener listener;
    private MutableLiveData<GeneralResponse> loginResponseMutableLiveData;

    public EnterNewPasswordViewModel(LoginListener listener) {
        this.listener = listener;
        authRepo = new AuthRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }

    public MutableLiveData<GeneralResponse> getForgetPasswordObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }

    public void login(Context context) {
        authRepo.resetPassword(context, token, password, loginResponseMutableLiveData);

    }

    public void onRegisterClick(View view) {
        Intent intent = new Intent(view.getContext(), RegisterActivity.class);
        //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        view.getContext().startActivity(intent);

    }

    public void onResetPasswordClick(View view) {
        if (MainApplication.isConnected) {
            if (listener != null)
                listener.validation();
            if ((password != null && !password.isEmpty()) && (confirmPassword != null && !confirmPassword.isEmpty()) && (password.equals(confirmPassword))) {
                setLoginVisibility(View.GONE);
                setPbLoginVisibility(View.VISIBLE);

                login(view.getContext());
            }
        } else
            Snackbar.make(view, view.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

    }

    public TextWatcher passwordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        };
    }

    public TextWatcher passwordConfirmTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmPassword = s.toString();
            }
        };
    }

    public ObservableInt getLoginVisibility() {
        return loginVisibility;
    }

    public ObservableInt getPbLoginVisibility() {
        return pbLoginVisibility;
    }
}
