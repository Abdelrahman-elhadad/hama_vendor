package hama.alsaygh.kw.vendor.view.generalSettings.changePassword;

import android.content.Context;
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
import hama.alsaygh.kw.vendor.model.user.UserResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;

public class ChangePasswordViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";

    private final ProfileRepo authRepo;
    private String password = "", confirmPassword = "", oldPassword = "";

    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    private final LoginListener listener;
    private MutableLiveData<UserResponse> loginResponseMutableLiveData;

    public ChangePasswordViewModel(LoginListener listener) {
        this.listener = listener;
        authRepo = new ProfileRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }

    public MutableLiveData<UserResponse> getChangePasswordObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }

    public String getOldPassword() {
        return oldPassword;
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
        authRepo.updatePassword(context, oldPassword, password, loginResponseMutableLiveData);
    }


    public void onResetPasswordClick(View view) {

        if (MainApplication.isConnected) {
            if (listener != null)
                listener.validation();
            if ((oldPassword != null && !oldPassword.isEmpty()) && (password != null && !password.isEmpty()) && (confirmPassword != null && !confirmPassword.isEmpty()) && (password.equals(confirmPassword))) {
                setLoginVisibility(View.GONE);
                setPbLoginVisibility(View.VISIBLE);

                login(view.getContext());
            }
        } else
            Snackbar.make(view, view.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

    }

    public TextWatcher oldPasswordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                oldPassword = s.toString();
            }
        };
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
