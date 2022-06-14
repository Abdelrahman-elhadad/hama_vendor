package hama.alsaygh.kw.vendor.view.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;

import hama.alsaygh.kw.vendor.databinding.FragmentOrdersBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.order.adapter.AdapterPagerMyOrder;

public class OrdersFragment extends BaseFragment implements OnGeneralClickListener {

    FragmentOrdersBinding binding;
    OrdersViewModel model;
    FragmentManager fragmentManager;
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
        fragmentManager = getChildFragmentManager();
        model = new OrdersViewModel();
        binding.setModel(model);
        AdapterPagerMyOrder adapterPagerMyOrder = new AdapterPagerMyOrder(requireContext(), fragmentManager);
        binding.vpOrders.setAdapter(adapterPagerMyOrder);
        binding.tabOrders.setupWithViewPager(binding.vpOrders);
        binding.vpOrders.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabOrders));


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