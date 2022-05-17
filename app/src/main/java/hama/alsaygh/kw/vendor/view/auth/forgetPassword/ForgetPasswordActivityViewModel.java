package hama.alsaygh.kw.vendor.view.auth.forgetPassword;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.repo.AuthRepo;
import hama.alsaygh.kw.vendor.view.auth.login.LoginActivity;

public class ForgetPasswordActivityViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";

    private final AuthRepo authRepo;
    private String userName;

    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    private final LoginListener listener;
    private MutableLiveData<GeneralResponse> loginResponseMutableLiveData;

    public ForgetPasswordActivityViewModel(LoginListener listener) {
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
        authRepo.forgetPassword(context, userName, loginResponseMutableLiveData);

    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        view.getContext().startActivity(intent);

    }

    public void onResetClick(View view) {

        if (listener != null)
            listener.validation();
        if (userName != null && !userName.isEmpty()) {
            setLoginVisibility(View.GONE);
            setPbLoginVisibility(View.VISIBLE);

            login(view.getContext());
        }
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
}
