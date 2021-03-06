package hama.alsaygh.kw.vendor.view.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import hama.alsaygh.kw.vendor.databinding.FragmentOrdersBinding;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

public class OrdersFragment extends BaseFragment {

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
        model.init(requireContext(), binding.vpOrders, binding.tabOrders, fragmentManager);
    }

}