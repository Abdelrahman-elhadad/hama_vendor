package com.example.hamavendor.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.hamavendor.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Orders extends BaseFragment {

    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    ImageView imageback;
    Button orderTracking;
    TabLayout tabLayout;
    TabItem complited, pending, canceled;
    ViewPager viewPager;
    //ArrayList<MyorderArray> myordersArray;
    TextView txt_toolbar;
    LinearLayout parent_my_order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order, container, false);



        fragmentManager=getChildFragmentManager();
        tabLayout =(TabLayout)view.findViewById(R.id.tab_myorder);
        complited =(TabItem)view.findViewById(R.id.complete_tab);
        pending =(TabItem)view.findViewById(R.id.pending_order_tab);
        canceled =(TabItem)view.findViewById(R.id.cancel_tab);
        viewPager =(ViewPager)view.findViewById(R.id.view_pager_myorder);
      //  AdapterPagerMyOrder adapterPagerMyOrder = new AdapterPagerMyOrder(fragmentManager,tabLayout.getTabCount());
       // viewPager.setAdapter(adapterPagerMyOrder);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        imageback = (ImageView) view.findViewById(R.id.back_myorder);
        imageback.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
//                                             fragmentTransaction = fragmentManager.beginTransaction();
//                                             fragmentTransaction.replace(R.id.liner1, new MyProfile());
//                                           //  fragmentTransaction.addToBackStack(null);
//                                             fragmentTransaction.commit();
                                             getActivity().onBackPressed();


                                         }
                                     }
        );
        tabLayout.setTabIndicatorFullWidth(false);
        tabLayout.getTabAt(0).setText(R.string.Completed);
        tabLayout.getTabAt(1).setText(R.string.Pending);
        tabLayout.getTabAt(2).setText(R.string.Canceled);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                  //  tab.setCustomView(R.layout.tab_title);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //  textView.setTextColor(tabLayout.getTabTextColors());
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                tabLayout.setTabIndicatorFullWidth(false);

    }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        return view;

    }
    }
