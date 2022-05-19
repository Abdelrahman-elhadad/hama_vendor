package hama.alsaygh.kw.vendor.fragment;

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

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class OffersActiveOffers extends BaseFragment {


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
        View view = inflater.inflate(R.layout.offers_active_offers, container, false);

        fragmentManager = getChildFragmentManager();
        tabLayout = (TabLayout) view.findViewById(R.id.tab_myorder);
        complited = (TabItem) view.findViewById(R.id.complete_tab);
        pending = (TabItem) view.findViewById(R.id.pending_order_tab);
        canceled = (TabItem) view.findViewById(R.id.cancel_tab);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager_myorder);

        fragmentManager = getChildFragmentManager();
        tabLayout = (TabLayout) view.findViewById(R.id.tab_myorder);
        complited = (TabItem) view.findViewById(R.id.complete_tab);
        pending = (TabItem) view.findViewById(R.id.pending_order_tab);
        canceled = (TabItem) view.findViewById(R.id.cancel_tab);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager_myorder);

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
        tabLayout.getTabAt(0).setText(R.string.Active_Offers);
        tabLayout.getTabAt(1).setText(R.string.Add_New_Offer);


        return  view;
    }
}