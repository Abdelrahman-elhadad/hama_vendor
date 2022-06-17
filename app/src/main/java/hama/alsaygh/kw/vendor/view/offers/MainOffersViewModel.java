package hama.alsaygh.kw.vendor.view.offers;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hama.alsaygh.kw.vendor.view.offers.adapter.AdapterPagerOffers;

public class MainOffersViewModel extends ViewModel {

    private final String TAG = "ProductsViewModel";
    public static final int ADD_OFFER = 1;
    public static final int ACTIVE_OFFER = 2;

    public MainOffersViewModel() {
    }


    public void init(Context context, ViewPager vpOrders, TabLayout tabOrders, FragmentManager fragmentManager) {
        AdapterPagerOffers adapterPagerMyOrder = new AdapterPagerOffers(context, fragmentManager);
        vpOrders.setAdapter(adapterPagerMyOrder);
        tabOrders.setupWithViewPager(vpOrders);
        vpOrders.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabOrders));

        //tabOrders.selectTab(tabOrders.getTabAt(1));

    }
}
