package hama.alsaygh.kw.vendor.view.home.fragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.LocalUtils;

public class BeastProductViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Product storeModel;
    OnGeneralClickListener onGeneralClickListener;
    int position;

    public BeastProductViewModel(Context context, Product storeModel, OnGeneralClickListener onGeneralClickListener, int position) {
        this.context = context;
        this.storeModel = storeModel;
        this.onGeneralClickListener = onGeneralClickListener;
        this.position = position;
    }

    public String getName() {
        if (isEnglish())
            return storeModel.getTranslations().getEn().getName();
        else
            return storeModel.getTranslations().getAr().getName();
    }


    public String getOrderCount() {
        return context.getString(R.string.name1).replace("xx", storeModel.getOrder_count() + "");
    }


    public String getImage() {
        String image = "";
        if (storeModel.getMedia() != null && !storeModel.getMedia().isEmpty()) {
            image = storeModel.getMedia().get(0).getLink();
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