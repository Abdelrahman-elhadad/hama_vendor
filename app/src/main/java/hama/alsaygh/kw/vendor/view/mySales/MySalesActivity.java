package hama.alsaygh.kw.vendor.view.mySales;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.ActivityMySalesBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class MySalesActivity extends BaseActivity implements LoginListener {

    ActivityMySalesBinding binding;
    MySalesViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMySalesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new MySalesViewModel();
        binding.setModel(model);
        binding.imgBack.setOnClickListener(v -> finish());

        model.setPieChartSetting(this, binding.chartAudiance);
        model.setPieChartSetting(this, binding.chartDelivery);
        model.setLineChartSetting(this, binding.chartSales);

        model.getObservable().observe(this, mySalesResponse -> {
            if (mySalesResponse.isStatus()) {

                if (mySalesResponse.getData().getGender_data().getMales() > 0 &&
                        mySalesResponse.getData().getGender_data().getFemale() > 0 &&
                        mySalesResponse.getData().getGender_data().getOthers() > 0) {

                    binding.chartAudiance.setVisibility(View.VISIBLE);
                    binding.tvNoAudianceData.setVisibility(View.GONE);
                    model.setAudiansData(this, binding.chartAudiance, mySalesResponse.getData());
                } else {
                    binding.chartAudiance.setVisibility(View.GONE);
                    binding.tvNoAudianceData.setVisibility(View.VISIBLE);
                }
                if (mySalesResponse.getData().getDelivery().getHand_by_hand() > 0 &&
                        mySalesResponse.getData().getDelivery().getHama() > 0) {

                    binding.chartDelivery.setVisibility(View.VISIBLE);
                    binding.tvNoDeliveryData.setVisibility(View.GONE);
                    model.setDeliveryData(this, binding.chartDelivery, mySalesResponse.getData());
                } else {
                    binding.chartDelivery.setVisibility(View.GONE);
                    binding.tvNoDeliveryData.setVisibility(View.VISIBLE);
                }

                model.setSalesData(this, binding.chartSales, mySalesResponse.getData());
                binding.tvSales.setText(model.getSalesAvg(this, mySalesResponse.getData()));
            } else {
                if (mySalesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.imageView14, mySalesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        model.login(this);

    }


    @Override
    public void validation() {

    }
}