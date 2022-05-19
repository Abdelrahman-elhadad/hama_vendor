package hama.alsaygh.kw.vendor.view.generalSettings.notification;

import android.content.Context;
import android.widget.CompoundButton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.user.User;
import hama.alsaygh.kw.vendor.model.user.UserResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;

public class NotificationSettingViewModel extends ViewModel {

    private final String TAG = "LanguageViewModel";
    private Context activity;

    MutableLiveData<UserResponse> languageResponseMutableLiveData;

    public MutableLiveData<UserResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void updateNotification(Context context) {
        User user = SharedPreferenceConstant.getSharedPreferenceUser(context);
        new ProfileRepo().updateNotificationSettings(context, user.isGeneral_notifications(), user.isOrder_notification(), user.isEvent_notification(), user.isAdv_notification(), languageResponseMutableLiveData);
    }

    public NotificationSettingViewModel(Context activity) {
        this.activity = activity;
    }

    public boolean isGeneral() {
        return SharedPreferenceConstant.getSharedPreferenceUser(activity).isGeneral_notifications();
    }

    public boolean isOrder() {
        return SharedPreferenceConstant.getSharedPreferenceUser(activity).isOrder_notification();
    }

    public boolean isEvent() {
        return SharedPreferenceConstant.getSharedPreferenceUser(activity).isEvent_notification();
    }

    public boolean isAds() {
        return SharedPreferenceConstant.getSharedPreferenceUser(activity).isAdv_notification();
    }

    public void onGeneralChanged(CompoundButton buttonView, boolean isChecked) {
        User user = SharedPreferenceConstant.getSharedPreferenceUser(buttonView.getContext());
        user.setGeneral_notifications(isChecked);
        SharedPreferenceConstant.setSharedPreferenceUser(buttonView.getContext(), user);
        updateNotification(buttonView.getContext());
    }

    public void onOrderChanged(CompoundButton buttonView, boolean isChecked) {
        User user = SharedPreferenceConstant.getSharedPreferenceUser(buttonView.getContext());
        user.setOrder_notification(isChecked);
        SharedPreferenceConstant.setSharedPreferenceUser(buttonView.getContext(), user);
        updateNotification(buttonView.getContext());
    }

    public void onEventChanged(CompoundButton buttonView, boolean isChecked) {
        User user = SharedPreferenceConstant.getSharedPreferenceUser(buttonView.getContext());
        user.setEvent_notification(isChecked);
        SharedPreferenceConstant.setSharedPreferenceUser(buttonView.getContext(), user);
        updateNotification(buttonView.getContext());
    }

    public void onAdsChanged(CompoundButton buttonView, boolean isChecked) {

        User user = SharedPreferenceConstant.getSharedPreferenceUser(buttonView.getContext());
        user.setAdv_notification(isChecked);
        SharedPreferenceConstant.setSharedPreferenceUser(buttonView.getContext(), user);
        updateNotification(buttonView.getContext());
    }
}
