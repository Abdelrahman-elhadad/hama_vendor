package hama.alsaygh.kw.vendor.view.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hama.alsaygh.kw.vendor.databinding.FragmentMoreBinding;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

public class MoreFragment extends BaseFragment {

    FragmentMoreBinding binding;
    MoreViewModel model;

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new MoreViewModel(requireContext());
        binding.setModel(model);


    }
}