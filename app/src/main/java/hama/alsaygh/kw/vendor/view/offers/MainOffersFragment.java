package hama.alsaygh.kw.vendor.view.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import hama.alsaygh.kw.vendor.databinding.FragmentMainOffersBinding;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

public class MainOffersFragment extends BaseFragment {

    FragmentMainOffersBinding binding;
    MainOffersViewModel model;
    FragmentManager fragmentManager;

    public static MainOffersFragment newInstance() {
        return new MainOffersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainOffersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        model = new MainOffersViewModel();
        binding.setModel(model);
        model.init(requireContext(), binding.vpOrders, binding.tabOrders, fragmentManager);
    }
}