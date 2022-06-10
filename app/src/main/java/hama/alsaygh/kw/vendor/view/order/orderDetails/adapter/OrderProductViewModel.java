package hama.alsaygh.kw.vendor.view.order.orderDetails.adapter;

import static hama.alsaygh.kw.vendor.view.order.OrdersViewModel.PENDING;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.cart.CartItem;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.LocalUtils;

public class OrderProductViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    CartItem storeModel;
    OnGeneralClickListener onGeneralClickListener;
    int position;
    int type;


    protected ObservableInt ivDeleteVisibility = new ObservableInt();


    public OrderProductViewModel(Context context, CartItem storeModel, int type, OnGeneralClickListener onGeneralClickListener, int position) {
        this.context = context;
        this.storeModel = storeModel;
        this.onGeneralClickListener = onGeneralClickListener;
        this.position = position;
        this.type = type;
        if (type == PENDING && !storeModel.getStatus().equalsIgnoreCase("accepted")) {
            ivDeleteVisibility.set(View.VISIBLE);
        } else
            ivDeleteVisibility.set(View.GONE);
    }

    public String getName() {
        return storeModel.getProduct().getName();
    }

    public String getPrice() {
        return storeModel.getTotal() + " " + context.getString(R.string.currency);
    }


    public String getImage() {
        String image = "";
        Product product = storeModel.getProduct();
        if (product != null) {
            if (product.getMedia() != null && !product.getMedia().isEmpty())
                image = product.getMedia().get(0).getLink();
        }

        return image;
    }


    public ObservableInt getIvDeleteVisibility() {
        return ivDeleteVisibility;
    }

    public boolean isEnglish() {
        return LocalUtils.getInstance().getLanguageShort(context).equalsIgnoreCase("en");
    }

    public ImageView.ScaleType getScaleType() {

        return LocalUtils.getInstance().getLanguageShort(context).equalsIgnoreCase("en") ? ImageView.ScaleType.FIT_START : ImageView.ScaleType.FIT_END;
    }

    public void onItemClick(View view) {
        if (onGeneralClickListener != null)
            onGeneralClickListener.onItemClick(storeModel, position);
    }

    public void onItemDeleteClick(View view) {
        if (onGeneralClickListener != null)
            onGeneralClickListener.onDeleteClick(storeModel, position);
    }

}