package hama.alsaygh.kw.vendor.view.products.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.dialog.DeleteProduct;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.LocalUtils;
import hama.alsaygh.kw.vendor.utils.Utils;

public class StoreProductViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Product storeModel;
    OnGeneralClickListener onGeneralClickListener;
    int position;
    private final FragmentManager fragmentManager;

    public StoreProductViewModel(Context context, Product storeModel, FragmentManager fragmentManager, OnGeneralClickListener onGeneralClickListener, int position) {
        this.context = context;
        this.storeModel = storeModel;
        this.onGeneralClickListener = onGeneralClickListener;
        this.position = position;
        this.fragmentManager = fragmentManager;
    }

    public String getName() {
        return storeModel.getName();
    }


    public String getPrice() {
        return storeModel.getPrice() + " " + context.getString(R.string.currency);
    }

    public String getRevenuePrice() {
        return context.getString(R.string.pro).replace("xx", Utils.formatNumber(storeModel.getOrder_count() * storeModel.getPrice())) + " " + context.getString(R.string.currency);
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

    public void onItemClick(View view) {
        if (onGeneralClickListener != null)
            onGeneralClickListener.onItemClick(storeModel, position);
    }

    public void onMoreClick(View view) {
        showPopupWindow(view);
    }

    private void showPopupWindow(View v) {
        // inflate the layout of the popup window

        View popupView = View.inflate(v.getContext(), R.layout.popup_window, null);

        AppCompatTextView tvEdit = popupView.findViewById(R.id.tv_edit);
        AppCompatTextView tvDelete = popupView.findViewById(R.id.tv_delete);

        // create the popup window
        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);


        tvEdit.setOnClickListener(v1 -> {

            if (onGeneralClickListener != null)
                onGeneralClickListener.onEditClick(storeModel, position);
            popupWindow.dismiss();
        });

        tvDelete.setOnClickListener(v12 -> {
            DeleteProduct.newInstance(storeModel.getId(), onGeneralClickListener).show(fragmentManager, "Delete");
            popupWindow.dismiss();
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        popupView.measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        int height = popupView.getMeasuredHeight();
        int width = popupView.getMeasuredWidth();
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAsDropDown(v);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}