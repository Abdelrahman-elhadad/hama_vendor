package hama.alsaygh.kw.vendor.view.order.orderDetails;

import static hama.alsaygh.kw.vendor.view.order.OrdersViewModel.CANCELED;
import static hama.alsaygh.kw.vendor.view.order.OrdersViewModel.COMPLETED;
import static hama.alsaygh.kw.vendor.view.order.OrdersViewModel.PENDING;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.order.Order;
import hama.alsaygh.kw.vendor.model.order.OrderResponse;
import hama.alsaygh.kw.vendor.repo.OrderRepo;

public class OrderDetailsViewModel extends ViewModel {

    private final String TAG = "OrderDetailsViewModel";

    private MutableLiveData<OrderResponse> languageResponseMutableLiveData;

    final private OrderRepo productRepo;

    Order storeModel;
    protected ObservableInt requiredProductUpVisibility = new ObservableInt();
    protected ObservableInt requiredProductDownVisibility = new ObservableInt();

    protected ObservableInt shippingUpVisibility = new ObservableInt();
    protected ObservableInt shippingDownVisibility = new ObservableInt();

    protected ObservableInt deliveryTypeUpVisibility = new ObservableInt();
    protected ObservableInt deliveryTypeDownVisibility = new ObservableInt();

    protected ObservableInt invoiceUpVisibility = new ObservableInt();
    protected ObservableInt invoiceDownVisibility = new ObservableInt();

    protected ObservableInt addProductVisibility = new ObservableInt();
    protected ObservableInt pbAddProductVisibility = new ObservableInt();

    private final Context context;

    public OrderDetailsViewModel(Context context) {
        productRepo = new OrderRepo();
        this.context = context;

    }

    public void setStoreModel(Order storeModel) {
        this.storeModel = storeModel;
        requiredProductUpVisibility.set(View.GONE);
        requiredProductDownVisibility.set(View.VISIBLE);
        shippingUpVisibility.set(View.VISIBLE);
        shippingDownVisibility.set(View.GONE);
        deliveryTypeUpVisibility.set(View.VISIBLE);
        deliveryTypeDownVisibility.set(View.GONE);
        invoiceUpVisibility.set(View.VISIBLE);
        invoiceDownVisibility.set(View.GONE);

        addProductVisibility.set(View.GONE);
        pbAddProductVisibility.set(View.GONE);

    }

    public MutableLiveData<OrderResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void getOrders(Context context, int id, int type) {
        String status = "";
        switch (type) {
            case PENDING:
                status = "pending";
                break;

            case COMPLETED:
                status = "completed";
                break;

            case CANCELED:
                status = "canceled";
                break;
        }
        productRepo.getOrder(context, id, status, languageResponseMutableLiveData);
    }

    public ObservableInt getRequiredProductDownVisibility() {
        return requiredProductDownVisibility;
    }

    public ObservableInt getRequiredProductUpVisibility() {
        return requiredProductUpVisibility;
    }

    public ObservableInt getShippingDownVisibility() {
        return shippingDownVisibility;
    }

    public ObservableInt getShippingUpVisibility() {
        return shippingUpVisibility;
    }

    public ObservableInt getDeliveryTypeDownVisibility() {
        return deliveryTypeDownVisibility;
    }

    public ObservableInt getDeliveryTypeUpVisibility() {
        return deliveryTypeUpVisibility;
    }

    public ObservableInt getInvoiceDownVisibility() {
        return invoiceDownVisibility;
    }

    public ObservableInt getInvoiceUpVisibility() {
        return invoiceUpVisibility;
    }

    public ObservableInt getAddProductVisibility() {
        return addProductVisibility;
    }

    public ObservableInt getPbProductVisibility() {
        return pbAddProductVisibility;
    }


    public void onRequiredProductClick(View v) {
        if (requiredProductUpVisibility.get() == View.VISIBLE) {
            requiredProductUpVisibility.set(View.GONE);
            requiredProductDownVisibility.set(View.VISIBLE);
        } else {
            requiredProductUpVisibility.set(View.VISIBLE);
            requiredProductDownVisibility.set(View.GONE);
        }
    }

    public void onShippingClick(View v) {
        if (shippingUpVisibility.get() == View.VISIBLE) {
            shippingUpVisibility.set(View.GONE);
            shippingDownVisibility.set(View.VISIBLE);
        } else {
            shippingUpVisibility.set(View.VISIBLE);
            shippingDownVisibility.set(View.GONE);
        }
    }

    public void onDeliveryTypeClick(View v) {
        if (deliveryTypeUpVisibility.get() == View.VISIBLE) {
            deliveryTypeUpVisibility.set(View.GONE);
            deliveryTypeDownVisibility.set(View.VISIBLE);
        } else {
            deliveryTypeUpVisibility.set(View.VISIBLE);
            deliveryTypeDownVisibility.set(View.GONE);
        }
    }

    public void onInvoiceClick(View v) {
        if (invoiceUpVisibility.get() == View.VISIBLE) {
            invoiceUpVisibility.set(View.GONE);
            invoiceDownVisibility.set(View.VISIBLE);
        } else {
            invoiceUpVisibility.set(View.VISIBLE);
            invoiceDownVisibility.set(View.GONE);
        }
    }

    public String getUserName() {
        String name = "";
        if (storeModel != null)
            name = storeModel.getUser_info().getName();
        return name;
    }

    public String getCountry() {
        String name = "";
        if (storeModel != null)
            name = storeModel.getDelivery().getCountry().getName();
        return name;
    }

    public String getCity() {
        String name = "";
        if (storeModel != null)
            name = storeModel.getDelivery().getStreet();
        return name;
    }

    public String getZipCode() {
        String name = "";
        if (storeModel != null)
            name = storeModel.getDelivery().getZip_code();
        return name;
    }

    public String getBuildingNo() {
        String name = "";
        if (storeModel != null)
            name = storeModel.getDelivery().getBuilding_no();
        return name;
    }

    public String getDeliveryType() {
        String name = "";
        if (storeModel != null)
            name = storeModel.getDelivery_type_str();
        return name;
    }

    public String getDeliveryDate() {
        String name = "";
        if (storeModel != null && storeModel.getReceipt_at() != null)
            name = storeModel.getReceipt_at();
        return name;
    }

    public String getDeliveryTime() {
        String name = "";
        if (storeModel != null && storeModel.getReceipt_at() != null)
            name = storeModel.getReceipt_at();
        return name;
    }

    public String getItems() {
        String counts = "";
        if (storeModel != null)
            counts = storeModel.getItems_count() + " " + context.getString(R.string.productt);
        return counts;
    }

    public String getTotalPrice() {
        String price = "";
        if (storeModel != null)
            price = storeModel.getTotal();
        return price;
    }

    public String getTotalPriceAccept() {
        String price = context.getString(R.string.accept_order).replace("xx", "");
        if (storeModel != null)
            price = context.getString(R.string.accept_order).replace("xx", storeModel.getTotal());
        return price;
    }
}
