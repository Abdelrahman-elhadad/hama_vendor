package hama.alsaygh.kw.vendor.view.marketPrice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;

import hama.alsaygh.kw.vendor.R;
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
        binding.tabOrders.setTabTextColors(ContextCompat.getColor(MarketPriceActivity.this, R.color.color_store_page), ContextCompat.getColor(MarketPriceActivity.this, R.color.ghhg));
        int length = binding.tabOrders.getTabCount();
        for (int i = 0; i < length; i++) {
            if (binding.tabOrders.getTabAt(i) != null)
                binding.tabOrders.getTabAt(i).setCustomView(R.layout.tab_title);
        }
        binding.tabOrders.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_title);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //  textsetTextColor(tabLayout.getTabTextColors());
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                binding.tabOrders.setTabIndicatorFullWidth(false);

                textView.setTextColor(ContextCompat.getColor(MarketPriceActivity.this, R.color.ghhg));
                textView.setBackground(ContextCompat.getDrawable(MarketPriceActivity.this, R.drawable.background_blue_tab));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_title);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTypeface(Typeface.DEFAULT);
                textView.setTextColor(ContextCompat.getColor(MarketPriceActivity.this, R.color.color_store_page));
                textView.setBackground(null);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        binding.tabOrders.selectTab(binding.tabOrders.getTabAt(1));


    }
}