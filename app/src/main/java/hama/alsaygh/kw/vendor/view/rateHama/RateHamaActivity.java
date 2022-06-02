package hama.alsaygh.kw.vendor.view.rateHama;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.ActivityRateHamaBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class RateHamaActivity extends BaseActivity implements LoginListener {
    ActivityRateHamaBinding binding;
    RateHamaViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRateHamaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new RateHamaViewModel(this);
        binding.setModel(model);

        binding.imgBack.setOnClickListener(v -> finish());
        binding.textView96.setOnClickListener(v -> finish());
        model.getObservable().observe(this, loginResponse -> {

            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {


                Snackbar.make(binding.textView9, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            } else {
                if (loginResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.textView9, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void validation() {
        model.setLoginVisibility(View.GONE);
        model.setPbLoginVisibility(View.VISIBLE);
        model.setRating(binding.ratRestaurant2.getRating());
        model.login(this);
    }
}