package hama.alsaygh.kw.vendor.view.marketPrice;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import hama.alsaygh.kw.vendor.databinding.ActivityMarketPriceBinding;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.marketPrice.adapter.MarketPriceAdapterPager;

public class MarketPriceActivity extends BaseActivity {

    ActivityMarketPriceBinding binding;
    MarketPriceViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketPriceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new MarketPriceViewModel();
        binding.setModel(model);
        binding.toolbarImg.setOnClickListener(v -> finish());

        MarketPriceAdapterPager adapterPagerMyOrder = new MarketPriceAdapterPager(MarketPriceActivity.this, getSupportFragmentManager());
        binding.vpOrders.setAdapter(adapterPagerMyOrder);
        binding.tabOrders.setupWithViewPager(binding.vpOrders);
        binding.vpOrders.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabOrders));
    }
}