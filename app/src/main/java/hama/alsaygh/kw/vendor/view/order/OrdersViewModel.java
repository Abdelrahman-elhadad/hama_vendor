package hama.alsaygh.kw.vendor.view.order;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

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

}
