package hama.alsaygh.kw.vendor.view.home;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.fragment.Home;
import hama.alsaygh.kw.vendor.fragment.MyProductsFragment;
import hama.alsaygh.kw.vendor.fragment.OffersActiveOffers;
import hama.alsaygh.kw.vendor.fragment.Orders;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ButtomNavigation";
    LinearLayout linearLayout;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    private hama.alsaygh.kw.vendor.fragment.Orders order;
  //  private Home home ;
    public final int Home = 0;

    public final int  MyProductsFragment =1;
    public final int Orders = 2;
    public final int OffersActiveOffers =2;

    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));
        setContentView(R.layout.buttom_navigation);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.buttom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // bottomNavigationView.setItemIconTintList(null);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        fragmentManager = getSupportFragmentManager();

    }

    private void commitFragment(Fragment fragment, int position) {
        // HomeActivity.position = position;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.liner1, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.page_1) {
            commitFragment(new Home(), Home);
            Log.e(TAG, "order" + item.getItemId());

            return true;
        }
        if (item.getItemId() == R.id.page_3) {
            commitFragment(new MyProductsFragment(), MyProductsFragment);
            Log.e(TAG, "cart" + item.getItemId());
            return true;
        }
        if (item.getItemId() == R.id.page_4) {
            commitFragment(new Orders(), Orders);
            Log.e(TAG, "stores" + item.getItemId());

            return true;
        }
        if (item.getItemId() == R.id.page_5) {
            commitFragment(new OffersActiveOffers(),OffersActiveOffers );
            Log.e(TAG, "search" + item.getItemId());

            return true;
        }
//        if (item.getItemId() == R.id.page_6) {
//
//            if (position == Setting) {
//                commitFragment(new Setting(), Setting);
//            } else if (position == SettingLanguage) {
//                commitFragment(new SettingLanguage(), SettingLanguage);
//            } else
//                commitFragment(new MyProfile(), MyProfile);
//            Log.e(TAG, "myprofile" + item.getItemId());
//
//            return true;
//        }
        return false;

        }
    }
