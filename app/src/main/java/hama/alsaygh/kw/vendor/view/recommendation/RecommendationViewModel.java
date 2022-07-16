package hama.alsaygh.kw.vendor.view.recommendation;

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
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;

public class RecommendationViewModel extends ViewModel {

    private final String TAG = "RecommendationViewModel";

    private final ProfileRepo authRepo;
    private String msg;

    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    private final LoginListener listener;
    private MutableLiveData<GeneralResponse> loginResponseMutableLiveData;


    public RecommendationViewModel(LoginListener listener) {
        this.listener = listener;
        authRepo = new ProfileRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }


    public MutableLiveData<GeneralResponse> getObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }


    public String getMsg() {
        return msg;
    }

    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }

    public void login(Context context) {
        authRepo.suggestion(context, msg, loginResponseMutableLiveData);

    }

    public void onResetClick(View view) {
        if (MainApplication.isConnected) {
            if (listener != null)
                listener.validation();
            if (msg != null && !msg.isEmpty()) {
                setLoginVisibility(View.GONE);
                setPbLoginVisibility(View.VISIBLE);

                login(view.getContext());
            }
        } else
            Snackbar.make(view, view.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

    }


    public TextWatcher msgTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                msg = s.toString();
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
