package hama.alsaygh.kw.vendor.view.order.statusOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.FragmentOrdersStatusBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.order.Order;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.order.OrdersViewModel;
import hama.alsaygh.kw.vendor.view.order.orderDetails.OrderDetailsActivity;
import hama.alsaygh.kw.vendor.view.order.statusOrder.adapter.OrdersRecycleViewAdapter;

public class OrdersStatusFragment extends BaseFragment implements OnGeneralClickListener {

    FragmentOrdersStatusBinding binding;
    OrdersViewModel model;
    int type;
    Skeleton skeleton;
    FragmentManager fragmentManager;

    public static OrdersStatusFragment newInstance(int type) {
        OrdersStatusFragment fragment = new OrdersStatusFragment();
        fragment.setType(type);
        return fragment;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrdersStatusBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        model = new OrdersViewModel();
        binding.setModel(model);
        skeleton = SkeletonLayoutUtils.applySkeleton(binding.rvOrders, R.layout.item_rv_orders, 2);
        Utils.getInstance().setSkeletonMaskAndShimmer(requireContext(), skeleton);
        model.getObserver().observe(requireActivity(), productsResponse -> {
            skeleton.showOriginal();
            if (productsResponse.isStatus()) {
                if (productsResponse.getData().isEmpty()) {

                    binding.rvOrders.setVisibility(View.GONE);
                    binding.llNoOrders.setVisibility(View.VISIBLE);

                } else {
                    binding.llNoOrders.setVisibility(View.GONE);
                    binding.rvOrders.setVisibility(View.VISIBLE);

                    OrdersRecycleViewAdapter adapter = new OrdersRecycleViewAdapter(productsResponse.getData(), type, OrdersStatusFragment.this);
                    binding.rvOrders.setAdapter(adapter);
                }
            } else {

                if (productsResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(fragmentManager, "login");
                } else {
                    Snackbar.make(binding.rvOrders, productsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        skeleton.showSkeleton();
        model.getOrders(requireContext(), type);
    }

    @Override
    public void onItemClick(Object object, int position) {
        if (object instanceof Order) {
            Order order = (Order) object;
            Intent intent = new Intent(requireContext(), OrderDetailsActivity.class);
            intent.putExtra(AppConstants.ORDER_ID, order.getId());
            intent.putExtra(AppConstants.ORDER_STATUS, type);
            startActivity(intent);
        }
    }

    @Override
    public void onEditClick(Object object, int position) {

    }

    @Override
    public void onDeleteClick(Object object, int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.getObserver().removeObservers(requireActivity());
    }


}