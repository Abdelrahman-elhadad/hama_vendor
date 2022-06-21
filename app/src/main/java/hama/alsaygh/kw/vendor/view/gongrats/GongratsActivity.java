package hama.alsaygh.kw.vendor.view.gongrats;

import android.content.Intent;
import android.os.Bundle;

import hama.alsaygh.kw.vendor.databinding.ActivityGongratsBinding;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.order.orderDetails.OrderDetailsActivity;

public class GongratsActivity extends BaseActivity {

    ActivityGongratsBinding binding;
    GongratsViewModel model;

    int status;
    int order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGongratsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent() != null) {
            status = getIntent().getIntExtra(AppConstants.ORDER_STATUS, -1);
            order_id = getIntent().getIntExtra(AppConstants.ORDER_ID, -1);
        }
        model = new GongratsViewModel();
        binding.setModel(model);

        binding.tvBackToOrder.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(v.getContext(), OrderDetailsActivity.class);
            intent.putExtra(AppConstants.ORDER_ID, order_id);
            intent.putExtra(AppConstants.ORDER_STATUS, status);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra(AppConstants.ORDER_ID, order_id);
        intent.putExtra(AppConstants.ORDER_STATUS, status);
        startActivity(intent);
    }
}