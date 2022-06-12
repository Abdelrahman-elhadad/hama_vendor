package hama.alsaygh.kw.vendor.view.notification;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.notifications.NotificationsResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;

public class NotificationsViewModel extends ViewModel {

    private final String TAG = "SplashActivityViewModel";

    Context context;
    private MutableLiveData<NotificationsResponse> notificationsResponseMutableLiveData;

    ProfileRepo profileRepo;

    public NotificationsViewModel(Context context) {
        this.context = context;
        profileRepo = new ProfileRepo();
    }

    public MutableLiveData<NotificationsResponse> getNotificationsObservable() {
        if (notificationsResponseMutableLiveData == null)
            notificationsResponseMutableLiveData = new MutableLiveData<>();

        return notificationsResponseMutableLiveData;
    }

    public void getNotification(int page) {
        profileRepo.getNotification(context, page, notificationsResponseMutableLiveData);
    }
}
