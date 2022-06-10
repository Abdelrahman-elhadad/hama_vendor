package hama.alsaygh.kw.vendor.view.order;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.FragmentOrdersBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.order.adapter.AdapterPagerMyOrder;

public class OrdersFragment extends BaseFragment implements OnGeneralClickListener {

    FragmentOrdersBinding binding;
    OrdersViewModel model;

    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new OrdersViewModel();
        binding.setModel(model);
        AdapterPagerMyOrder adapterPagerMyOrder = new AdapterPagerMyOrder(requireContext(), getChildFragmentManager());
        binding.vpOrders.setAdapter(adapterPagerMyOrder);
        binding.tabOrders.setupWithViewPager(binding.vpOrders);
        binding.vpOrders.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabOrders));
        binding.tabOrders.setTabTextColors(ContextCompat.getColor(requireContext(), R.color.color_store_page), ContextCompat.getColor(requireContext(), R.color.ghhg));
        int length = binding.tabOrders.getTabCount();
        for (int i = 0; i < length; i++) {
            if (binding.tabOrders.getTabAt(i) != null)
                binding.tabOrders.getTabAt(i).setCustomView(R.layout.tab_title);
        }
        binding.tabOrders.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_title);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //  textsetTextColor(tabLayout.getTabTextColors());
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                binding.tabOrders.setTabIndicatorFullWidth(false);

                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.ghhg));
                textView.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.background_blue_tab));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_title);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTypeface(Typeface.DEFAULT);
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_store_page));
                textView.setBackground(null);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        binding.tabOrders.selectTab(binding.tabOrders.getTabAt(1));

    }


    @Override
    public void onItemClick(Object object, int position) {


    }

    @Override
    public void onEditClick(Object object, int position) {

    }

    @Override
    public void onDeleteClick(Object object, int position) {

    }


}