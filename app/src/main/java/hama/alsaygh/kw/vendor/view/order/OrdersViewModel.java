package hama.alsaygh.kw.vendor.view.order;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.model.order.OrdersResponse;
import hama.alsaygh.kw.vendor.repo.OrderRepo;
import hama.alsaygh.kw.vendor.view.order.adapter.AdapterPagerMyOrder;

public class OrdersViewModel extends ViewModel {

    private final String TAG = "ProductsViewModel";

    private MutableLiveData<OrdersResponse> languageResponseMutableLiveData;

    final private OrderRepo productRepo;
    public static final int PENDING = 1;
    public static final int COMPLETED = 2;
    public static final int CANCELED = 3;
    public static final int IN_PROGRESS = 4;

    int type = -1;

    public void setType(int type) {
        this.type = type;
    }

    public OrdersViewModel() {
        productRepo = new OrderRepo();

    }

    public MutableLiveData<OrdersResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void getOrders(Context context, int type) {
        this.setType(type);
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
            case IN_PROGRESS:
                status = "in_progress";
                break;
        }
        productRepo.getOrders(context, status, languageResponseMutableLiveData);
    }

    public void init(Context context, ViewPager vpOrders, TabLayout tabOrders, FragmentManager fragmentManager) {
        AdapterPagerMyOrder adapterPagerMyOrder = new AdapterPagerMyOrder(context, fragmentManager);
        vpOrders.setAdapter(adapterPagerMyOrder);
        tabOrders.setupWithViewPager(vpOrders);
        vpOrders.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabOrders));

        tabOrders.selectTab(tabOrders.getTabAt(1));

    }

    private final ObservableInt isConnected = new ObservableInt();
    private final ObservableInt isNotConnectedView = new ObservableInt();

    public void setInternetConnection() {
        isConnected.set(View.VISIBLE);
        isNotConnectedView.set(View.GONE);
    }

    public void setNoInternetConnection() {
        isConnected.set(View.GONE);
        isNotConnectedView.set(View.VISIBLE);
    }

    public ObservableInt getIsConnected() {
        return isConnected;
    }

    public ObservableInt getIsNotConnectedView() {
        return isNotConnectedView;
    }

    public void onTryAgainClick(View view) {
        if (MainApplication.isConnected) {
            setInternetConnection();
            getOrders(view.getContext(), type);
        } else {
            setNoInternetConnection();
        }
    }


}
