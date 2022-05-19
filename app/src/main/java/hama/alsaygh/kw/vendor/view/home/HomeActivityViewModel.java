package hama.alsaygh.kw.vendor.view.home;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.fragment.HomeFragment;
import hama.alsaygh.kw.vendor.fragment.MyProductsFragment;
import hama.alsaygh.kw.vendor.fragment.OffersActiveOffers;
import hama.alsaygh.kw.vendor.fragment.Orders;
import hama.alsaygh.kw.vendor.model.onBoarding.OnBoardResponse;
import hama.alsaygh.kw.vendor.view.more.MoreFragment;

public class HomeActivityViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";
    private MutableLiveData<OnBoardResponse> onBoardingResponseMutableLiveData;
    private final ObservableInt progress = new ObservableInt();
    FragmentManager fragmentManager;

    //  private Home home ;
    private final int Home = 0;
    private final int MyProducts = 1;
    private final int Orders = 2;
    private final int Offers = 3;
    private final int more = 4;

    public HomeActivityViewModel(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public int getHome() {
        return Home;
    }

    public int getMyProducts() {
        return MyProducts;
    }

    public int getOrders() {
        return Orders;
    }

    public int getOffers() {
        return Offers;
    }

    public int getMore() {
        return more;
    }

    public void commitFragment(Fragment fragment, int position) {
        HomeActivity.position = position;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.liner1, fragment);
        fragmentTransaction.commit();
    }


    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view, NavigationBarView.OnItemSelectedListener listener) {
        view.setOnItemSelectedListener(listener);
    }


    public boolean onNavigationClick(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_home) {
            commitFragment(new HomeFragment(), Home);
            return true;
        }
        if (item.getItemId() == R.id.item_products) {
            commitFragment(new MyProductsFragment(), MyProducts);
            return true;
        }
        if (item.getItemId() == R.id.item_orders) {
            commitFragment(new Orders(), Orders);
            return true;
        }
        if (item.getItemId() == R.id.item_offers) {
            commitFragment(new OffersActiveOffers(), Offers);
            return true;
        }

        if (item.getItemId() == R.id.item_more) {
            commitFragment( MoreFragment.newInstance(), more);
            return true;
        }
        return false;
    }

}
