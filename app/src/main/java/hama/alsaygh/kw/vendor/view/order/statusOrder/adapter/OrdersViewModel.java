package hama.alsaygh.kw.vendor.view.order.statusOrder.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.order.Order;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.LocalUtils;

public class OrdersViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Order storeModel;
    OnGeneralClickListener onGeneralClickListener;
    int position;
    int type;

    protected ObservableInt rlImagesVisibility = new ObservableInt();
    protected ObservableInt iv1Visibility = new ObservableInt();
    protected ObservableInt iv2Visibility = new ObservableInt();
    protected ObservableInt iv3Visibility = new ObservableInt();
    protected ObservableInt iv4Visibility = new ObservableInt();
    protected ObservableInt tvMoreVisibility = new ObservableInt();
    protected ObservableInt ivVerticalVisibility = new ObservableInt();
    protected ObservableInt ivHorizontalVisibility = new ObservableInt();


    public OrdersViewModel(Context context, Order storeModel, int type, OnGeneralClickListener onGeneralClickListener, int position) {
        this.context = context;
        this.storeModel = storeModel;
        this.onGeneralClickListener = onGeneralClickListener;
        this.position = position;
        this.type = type;
        if (storeModel.getItems().isEmpty()) {
            rlImagesVisibility.set(View.INVISIBLE);
        } else {
            rlImagesVisibility.set(View.VISIBLE);
            if (storeModel.getItems().size() > 4) {
                iv1Visibility.set(View.VISIBLE);
                iv2Visibility.set(View.VISIBLE);
                iv3Visibility.set(View.VISIBLE);
                iv4Visibility.set(View.VISIBLE);
                tvMoreVisibility.set(View.VISIBLE);
                ivVerticalVisibility.set(View.VISIBLE);
                ivHorizontalVisibility.set(View.VISIBLE);
            } else if (storeModel.getItems().size() == 4) {
                iv1Visibility.set(View.VISIBLE);
                iv2Visibility.set(View.VISIBLE);
                iv3Visibility.set(View.VISIBLE);
                iv4Visibility.set(View.VISIBLE);
                tvMoreVisibility.set(View.GONE);
                ivVerticalVisibility.set(View.VISIBLE);
                ivHorizontalVisibility.set(View.VISIBLE);
            } else if (storeModel.getItems().size() > 3) {
                iv1Visibility.set(View.VISIBLE);
                iv2Visibility.set(View.VISIBLE);
                iv3Visibility.set(View.VISIBLE);
                iv4Visibility.set(View.GONE);
                tvMoreVisibility.set(View.GONE);
                ivVerticalVisibility.set(View.VISIBLE);
                ivHorizontalVisibility.set(View.VISIBLE);
            } else if (storeModel.getItems().size() > 2) {
                iv1Visibility.set(View.VISIBLE);
                iv2Visibility.set(View.VISIBLE);
                iv3Visibility.set(View.GONE);
                iv4Visibility.set(View.GONE);
                tvMoreVisibility.set(View.GONE);
                ivVerticalVisibility.set(View.GONE);
                ivHorizontalVisibility.set(View.VISIBLE);
            } else if (storeModel.getItems().size() > 1) {
                iv1Visibility.set(View.VISIBLE);
                iv2Visibility.set(View.GONE);
                iv3Visibility.set(View.GONE);
                iv4Visibility.set(View.GONE);
                tvMoreVisibility.set(View.GONE);
                ivVerticalVisibility.set(View.GONE);
                ivHorizontalVisibility.set(View.GONE);
            }
        }
    }

    public String getName() {
        return storeModel.getTitle();
    }

    public String getPrice() {
        return storeModel.getTotal();
    }

    public String getProducts() {
        return storeModel.getItems_count() + " " + context.getString(R.string.productt);
    }

    public String getCratedTime() {
        return storeModel.getCreateDate();
    }

    public String getMoreImages() {
        if (storeModel.getItems().isEmpty())
            return "";
        else if (storeModel.getItems().size() > 4)
            return "+" + (storeModel.getItems().size() - 4);
        return "";
    }


    public String getImage1() {
        String image = "";

        if (!storeModel.getItems().isEmpty() && storeModel.getItems().size() >= 1) {
            Product product = storeModel.getItems().get(0).getProduct();
            if (product != null) {
                if (product.getMedia() != null && !product.getMedia().isEmpty())
                    image = product.getMedia().get(0).getLink();
            }
        }
        return image;
    }

    public String getImage2() {
        String image = "";

        if (!storeModel.getItems().isEmpty() && storeModel.getItems().size() >= 2) {
            Product product = storeModel.getItems().get(1).getProduct();
            if (product != null) {
                if (product.getMedia() != null && !product.getMedia().isEmpty())
                    image = product.getMedia().get(0).getLink();
            }
        }
        return image;
    }

    public String getImage3() {
        String image = "";

        if (!storeModel.getItems().isEmpty() && storeModel.getItems().size() >= 3) {
            Product product = storeModel.getItems().get(2).getProduct();
            if (product != null) {
                if (product.getMedia() != null && !product.getMedia().isEmpty())
                    image = product.getMedia().get(0).getLink();
            }
        }
        return image;
    }

    public String getImage4() {
        String image = "";

        if (!storeModel.getItems().isEmpty() && storeModel.getItems().size() >= 4) {
            Product product = storeModel.getItems().get(3).getProduct();
            if (product != null) {
                if (product.getMedia() != null && !product.getMedia().isEmpty())
                    image = product.getMedia().get(0).getLink();
            }
        }
        return image;
    }


    public Drawable getStatusDrawable() {
        if (isEnglish()) {
            if (type == hama.alsaygh.kw.vendor.view.order.OrdersViewModel.PENDING) {
                return ContextCompat.getDrawable(context, R.drawable.shape_pending);
            } else if (type == hama.alsaygh.kw.vendor.view.order.OrdersViewModel.COMPLETED) {
                return ContextCompat.getDrawable(context, R.drawable.shape_completed);
            } else if (type == hama.alsaygh.kw.vendor.view.order.OrdersViewModel.CANCELED) {
                return ContextCompat.getDrawable(context, R.drawable.shape_canceled);

            }
        } else {
            if (type == hama.alsaygh.kw.vendor.view.order.OrdersViewModel.PENDING) {
                return ContextCompat.getDrawable(context, R.drawable.shape_pending_ar);
            } else if (type == hama.alsaygh.kw.vendor.view.order.OrdersViewModel.COMPLETED) {
                return ContextCompat.getDrawable(context, R.drawable.shape_completed_ar);
            } else if (type == hama.alsaygh.kw.vendor.view.order.OrdersViewModel.CANCELED) {
                return ContextCompat.getDrawable(context, R.drawable.shape_canceld_ar);

            }
        }
        return ContextCompat.getDrawable(context, R.drawable.shape_pending_ar);
    }

    public ObservableInt getRlImagesVisibility() {
        return rlImagesVisibility;
    }

    public ObservableInt getIv1Visibility() {
        return iv1Visibility;
    }

    public ObservableInt getIv2Visibility() {
        return iv2Visibility;
    }

    public ObservableInt getIv3Visibility() {
        return iv3Visibility;
    }

    public ObservableInt getIv4Visibility() {
        return iv4Visibility;
    }

    public ObservableInt getTvMoreVisibility() {
        return tvMoreVisibility;
    }

    public ObservableInt getIvVerticalVisibility() {
        return ivVerticalVisibility;
    }

    public ObservableInt getIvHorizontalVisibility() {
        return ivHorizontalVisibility;
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

}