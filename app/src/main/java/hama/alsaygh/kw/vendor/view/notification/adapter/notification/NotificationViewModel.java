package hama.alsaygh.kw.vendor.view.notification.adapter.notification;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.notifications.Notifications;


public class NotificationViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Notifications storeModel;

    public NotificationViewModel(Context context, Notifications storeModel) {
        this.context = context;
        this.storeModel = storeModel;
    }

    public String getName() {
        return storeModel.getContent();
    }


}