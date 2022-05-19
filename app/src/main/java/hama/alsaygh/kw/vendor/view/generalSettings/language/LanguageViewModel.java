package hama.alsaygh.kw.vendor.view.generalSettings.language;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.user.User;
import hama.alsaygh.kw.vendor.model.user.UserResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;
import hama.alsaygh.kw.vendor.utils.LocalUtils;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;

public class LanguageViewModel extends ViewModel {

    private final String TAG = "LanguageViewModel";
    private AppCompatActivity activity;

    MutableLiveData<UserResponse> languageResponseMutableLiveData;

    public MutableLiveData<UserResponse> getLanguageObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void sendLanguage(String la) {
        new ProfileRepo().changeLanguage(activity, la, languageResponseMutableLiveData);
    }

    public LanguageViewModel(AppCompatActivity activity) {
        this.activity = activity;
    }

    public boolean isEnglish() {
        return LocalUtils.getInstance().getLanguageShort(activity).equalsIgnoreCase("en");
    }

    public boolean isArabic() {
        return LocalUtils.getInstance().getLanguageShort(activity).equalsIgnoreCase("ar");
    }


    public void onArClick(View view) {
        sendLanguage("ar");
        LocalUtils.getInstance().setLanguage(activity, "ar");
        LocalUtils.getInstance().updateResources(activity, LocalUtils.getInstance().getLanguageShort(activity));

        SharedPreferenceConstant.getSharedPreferenceUser(activity).setLanguage("ar");
        User user=  SharedPreferenceConstant.getSharedPreferenceUser(activity);
        user.setLanguage("ar");
        SharedPreferenceConstant.setSharedPreferenceUser(view.getContext(),user);

        activity.finish();
        Intent intent = new Intent(activity, LanguageActivity.class);
        activity.startActivity(intent);

    }

    public void onEnClick(View view) {
        sendLanguage("en");
        LocalUtils.getInstance().setLanguage(activity, "en");
        LocalUtils.getInstance().updateResources(activity, LocalUtils.getInstance().getLanguageShort(activity));


        User user=  SharedPreferenceConstant.getSharedPreferenceUser(activity);
        user.setLanguage("en");
        SharedPreferenceConstant.setSharedPreferenceUser(view.getContext(),user);

        activity.finish();
        Intent intent = new Intent(activity, LanguageActivity.class);
        activity.startActivity(intent);
    }

    public void setLightStatusBarAndHide(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT); // optional
        }
        try {
            activity.getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
