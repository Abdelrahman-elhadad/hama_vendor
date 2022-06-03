package hama.alsaygh.kw.vendor.view.products.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import java.util.Locale;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.LocalUtils;

public class StoreProductViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Product storeModel;

    public StoreProductViewModel(Context context, Product storeModel) {
        this.context = context;
        this.storeModel = storeModel;
    }

    public String getName() {
        return storeModel.getName();
    }

    public String getPrice() {
        return storeModel.getPrice() + " " + context.getString(R.string.currency);
    }

    public String getOrderCount() {
        return context.getString(R.string.orders_num).replace("xx", storeModel.getOrder_count() + "");
    }

    public String getWeight() {
        return context.getString(R.string.weight) + " : " + String.format(Locale.ENGLISH, "%.3f", Double.parseDouble(storeModel.getWeight()));
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