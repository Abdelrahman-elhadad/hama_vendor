package hama.alsaygh.kw.vendor.view.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.FragmentHomeBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    FragmentHomeBinding binding;
    HomeFragmentViewModel model;
    FragmentManager fragmentManager;

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


        model.setLineChartSetting(requireContext(), binding.chartSales);

        model.getObservable().observe(requireActivity(), mySalesResponse -> {
            if (mySalesResponse.isStatus()) {


                model.setSalesData(requireContext(), binding.chartSales, mySalesResponse.getData());
                binding.tvSales.setText(model.getSalesAvg(requireContext(), mySalesResponse.getData()));
            } else {
                if (mySalesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(fragmentManager, "login");
                else
                    Snackbar.make(binding.tvSales, mySalesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        model.getMySales(requireContext());

    }
}