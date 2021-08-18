package com.example.hamavendor.activity;

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

import com.example.hamavendor.R;
import com.example.hamavendor.fragment.Home;
import com.example.hamavendor.fragment.MyProductsFragment;
import com.example.hamavendor.fragment.Orders;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ButtomNavigation extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ButtomNavigation";
    LinearLayout linearLayout;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    private Orders order;
    private Home home;
    private MyProductsFragment myProductsFragment;

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
            commitFragment(new Home(), home);
            Log.e(TAG, "order" + item.getItemId());

            return true;
        }
        if (item.getItemId() == R.id.page_3) {
            commitFragment(new MyProductsFragment(), myProductsFragment);
            Log.e(TAG, "cart" + item.getItemId());
            return true;
        }
        if (item.getItemId() == R.id.page_4) {
            commitFragment(new Orders(), order);
            Log.e(TAG, "stores" + item.getItemId());

            return true;
        }
        if (item.getItemId() == R.id.page_5) {
            commitFragment(new Saerch(), Search);
            Log.e(TAG, "search" + item.getItemId());

            return true;
        }
        if (item.getItemId() == R.id.page_6) {

            if (position == Setting) {
                commitFragment(new Setting(), Setting);
            } else if (position == SettingLanguage) {
                commitFragment(new SettingLanguage(), SettingLanguage);
            } else
                commitFragment(new MyProfile(), MyProfile);
            Log.e(TAG, "myprofile" + item.getItemId());

            return true;
        }
        return false;

    }
}