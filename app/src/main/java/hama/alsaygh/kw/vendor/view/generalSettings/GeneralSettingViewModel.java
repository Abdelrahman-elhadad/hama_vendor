package hama.alsaygh.kw.vendor.view.generalSettings;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.view.generalSettings.changePassword.ChangePasswordActivity;
import hama.alsaygh.kw.vendor.view.generalSettings.language.LanguageActivity;
import hama.alsaygh.kw.vendor.view.generalSettings.notification.NotificationSettingActivity;

public class GeneralSettingViewModel extends ViewModel {

    private final String TAG = "GeneralSettingViewModel";


    public GeneralSettingViewModel() {

    }


    public void onLanguageClick(View view) {

        Intent intent = new Intent(view.getContext(), LanguageActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onNotificationClick(View view) {
        Intent intent = new Intent(view.getContext(), NotificationSettingActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onChangePasswordClick(View view) {
        Intent intent = new Intent(view.getContext(), ChangePasswordActivity.class);
        view.getContext().startActivity(intent);
    }


    public void onSocialMediaClick(View view) {

    }

}
