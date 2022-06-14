package hama.alsaygh.kw.vendor.view.gongrats;

import android.os.Bundle;

import hama.alsaygh.kw.vendor.databinding.ActivityGongratsBinding;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class GongratsActivity extends BaseActivity {

    ActivityGongratsBinding binding;
    GongratsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGongratsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new GongratsViewModel();
        binding.setModel(model);

        binding.tvBackToOrder.setOnClickListener(v -> finish());
    }
}