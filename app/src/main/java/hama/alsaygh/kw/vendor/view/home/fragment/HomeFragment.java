package hama.alsaygh.kw.vendor.view.home.fragment;

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
import hama.alsaygh.kw.vendor.databinding.FragmentHomeBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.home.HomeActivity;
import hama.alsaygh.kw.vendor.view.home.fragment.adapter.BeastProductRecycleViewAdapter;
import hama.alsaygh.kw.vendor.view.home.fragment.adapter.UsersRecycleViewAdapter;

public class HomeFragment extends BaseFragment {

    FragmentHomeBinding binding;
    HomeFragmentViewModel model;
    FragmentManager fragmentManager;
    Skeleton skeleton;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        model = new HomeFragmentViewModel(requireContext(), fragmentManager);
        binding.setModel(model);
        skeleton = SkeletonLayoutUtils.createSkeleton(binding.nsMain);
        Utils.getInstance().setSkeletonMaskAndShimmer(requireContext(), skeleton);
        model.setLineChartSetting(requireContext(), binding.chartSales);
        model.setBarChartSetting(requireContext(), binding.chartBestDaySales);

        model.getObservable().observe(requireActivity(), mySalesResponse -> {
            skeleton.showOriginal();
            if (mySalesResponse.isStatus()) {
                model.setSalesData(requireContext(), binding.chartSales, mySalesResponse.getData());
                binding.tvSales.setText(model.getSalesAvg(requireContext(), mySalesResponse.getData()));

                model.setBestSalesData(requireContext(), binding.chartBestDaySales, mySalesResponse.getData());

                if (mySalesResponse.getData().getStore_info() != null) {
                    binding.tvAcceptablePrice.setText(mySalesResponse.getData().getStore_info().getLast_week_orders_avg());
                    binding.tvNewOrder.setText(mySalesResponse.getData().getStore_info().getLast_week_orders() + "");
                    binding.tvStoreViews.setText(mySalesResponse.getData().getStore_info().getVisitors_count() + "");
                    binding.tvAgeGroup.setText(mySalesResponse.getData().getStore_info().getMost_age_group());
                }
                if (mySalesResponse.getData().getBest_products() != null && !mySalesResponse.getData().getBest_products().isEmpty()) {
                    BeastProductRecycleViewAdapter productAdapter = new BeastProductRecycleViewAdapter(mySalesResponse.getData().getBest_products(), null);
                    binding.rvBeastProducts.setAdapter(productAdapter);
                    binding.llBeastProduct.setVisibility(View.VISIBLE);
                } else {
                    binding.llBeastProduct.setVisibility(View.GONE);
                }

                if (mySalesResponse.getData().getUser_rate() != null && !mySalesResponse.getData().getUser_rate().isEmpty()) {
                    UsersRecycleViewAdapter productAdapter = new UsersRecycleViewAdapter(mySalesResponse.getData().getUser_rate(), null);
                    binding.rvUser.setAdapter(productAdapter);
                    binding.llUsers.setVisibility(View.VISIBLE);
                } else {
                    binding.llUsers.setVisibility(View.GONE);
                }
            } else {
                if (mySalesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(fragmentManager, "login");
                else
                    Snackbar.make(binding.tvSales, mySalesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        skeleton.showSkeleton();
        model.getHome(requireContext());


        binding.tvSeeAllBeastProduct.setOnClickListener(v -> ((HomeActivity) requireActivity()).openProducts());

        model.getOrderPendingObserver().observe(requireActivity(), mySalesResponse -> {

            if (mySalesResponse.isStatus()) {
                if (mySalesResponse.getData() != null && !mySalesResponse.getData().isEmpty()) {
                    binding.llPendingOrder.setVisibility(View.VISIBLE);
                    String orders = requireContext().getString(R.string.order).replace("xx", mySalesResponse.getData().size() + "");
                    binding.tvOrderNewCount.setText(orders);
                } else {
                    binding.llPendingOrder.setVisibility(View.GONE);
                }
            } else {
                binding.llPendingOrder.setVisibility(View.GONE);
            }
        });
        model.getOrders(requireContext());
        binding.llPendingOrder.setOnClickListener(v -> ((HomeActivity) requireActivity()).openOrders());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.getOrderPendingObserver().removeObservers(requireActivity());
        model.getObservable().removeObservers(requireActivity());
    }
}