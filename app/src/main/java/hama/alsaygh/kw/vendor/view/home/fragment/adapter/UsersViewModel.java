package hama.alsaygh.kw.vendor.view.home.fragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.home.HomeUserData;
import hama.alsaygh.kw.vendor.utils.LocalUtils;

public class UsersViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    HomeUserData storeModel;
    OnGeneralClickListener onGeneralClickListener;
    int position;

    public UsersViewModel(Context context, HomeUserData storeModel, OnGeneralClickListener onGeneralClickListener, int position) {
        this.context = context;
        this.storeModel = storeModel;
        this.onGeneralClickListener = onGeneralClickListener;
        this.position = position;
    }

    public String getName() {

        return storeModel.getFull_name();
    }


    public String getImage() {
        String image = "";
        if (storeModel.getAvatar() != null && !storeModel.getAvatar().isEmpty()) {
            image = storeModel.getAvatar();
        }
        return image;
    }

    public boolean isEnglish() {
        return LocalUtils.getInstance().getLanguageShort(context).equalsIgnoreCase("en");
    }

    public ImageView.ScaleType getScaleType() {

        return LocalUtils.getInstance().getLanguageShort(context).equalsIgnoreCase("en") ? ImageView.ScaleType.FIT_START : ImageView.ScaleType.FIT_END;
    }
}