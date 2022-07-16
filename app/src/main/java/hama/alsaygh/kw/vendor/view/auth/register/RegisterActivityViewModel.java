package hama.alsaygh.kw.vendor.view.auth.register;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.model.user.LoginResponse;
import hama.alsaygh.kw.vendor.repo.AuthRepo;
import hama.alsaygh.kw.vendor.view.auth.login.LoginActivity;

public class RegisterActivityViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";

    private final AuthRepo authRepo;
    private String userName, password, email, phone, confirmPassword;

    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    private final LoginListener listener;
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData;

    public RegisterActivityViewModel(LoginListener listener) {
        this.listener = listener;
        authRepo = new AuthRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }

    public MutableLiveData<LoginResponse> getRegisterObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }

    public void register(Context context) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();

                    Log.d(TAG, token);
                    authRepo.register(context, token, userName, email, phone, password, loginResponseMutableLiveData);
                });

    }

    public void onLoginClick(View view) {

        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        view.getContext().startActivity(intent);
    }

    public void onRegisterClick(View view) {

        if (MainApplication.isConnected) {


            if (listener != null)
                listener.validation();
            if (isValid()) {
                setLoginVisibility(View.GONE);
                setPbLoginVisibility(View.VISIBLE);

                register(view.getContext());
            }
        } else
            Snackbar.make(view, view.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

    }

    public boolean isValid() {
        boolean isValid = true;


        if (userName == null || userName.isEmpty()) {
            isValid = false;
        }

        if (email == null || email.isEmpty()) {
            isValid = false;
        }

        if (phone == null || phone.isEmpty()) {
            isValid = false;
        }

        if (password == null || password.isEmpty()) {
            isValid = false;
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            isValid = false;
        }

        if ((confirmPassword != null && !confirmPassword.isEmpty()) && (password != null && !password.isEmpty())) {
            if (!password.equals(confirmPassword))
                isValid = false;
        }

        return isValid;
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

    public TextWatcher confirmPasswordTextWatcher() {
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

    public TextWatcher emailTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                email = s.toString();
            }
        };
    }

    public TextWatcher phoneTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phone = s.toString();
            }
        };
    }

    public void signUpNow(View view) {
        Intent i = new Intent(view.getContext(), RegisterActivity.class);
        view.getContext().startActivity(i);
    }

    public ObservableInt getLoginVisibility() {
        return loginVisibility;
    }

    public ObservableInt getPbLoginVisibility() {
        return pbLoginVisibility;
    }
}
