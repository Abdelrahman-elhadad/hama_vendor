package hama.alsaygh.kw.vendor.view.order;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.order.OrdersResponse;
import hama.alsaygh.kw.vendor.repo.OrderRepo;

public class OrdersViewModel extends ViewModel {

    private final String TAG = "ProductsViewModel";

    private MutableLiveData<OrdersResponse> languageResponseMutableLiveData;

    final private OrderRepo productRepo;
    public static final int PENDING = 1;
    public static final int COMPLETED = 2;
    public static final int CANCELED = 3;

    public OrdersViewModel() {
        productRepo = new OrderRepo();

    }

    public MutableLiveData<OrdersResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void getOrders(Context context, int type) {
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
        productRepo.getOrders(context, status, languageResponseMutableLiveData);
    }

}
