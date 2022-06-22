package hama.alsaygh.kw.vendor.view.gongrats.offers;

import android.os.Bundle;

import hama.alsaygh.kw.vendor.databinding.ActivityGongratsOfferBinding;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.gongrats.GongratsViewModel;

public class GongratsOfferActivity extends BaseActivity {

    ActivityGongratsOfferBinding binding;
    GongratsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGongratsOfferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new GongratsViewModel();
        binding.setModel(model);

        binding.tvBackToOrder.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}