package hama.alsaygh.kw.vendor.view.recommendation;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityRecommendationsBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class RecommendationActivity extends BaseActivity implements LoginListener {
    ActivityRecommendationsBinding binding;
    RecommendationViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecommendationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new RecommendationViewModel(this);
        binding.setModel(model);

        binding.imgBack.setOnClickListener(v -> finish());
        model.getObservable().observe(this, loginResponse -> {

            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {

                binding.edtMsg.setText("");

                Snackbar.make(binding.textView106, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            } else {
                if (loginResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.textView106, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void validation() {

        if (model.getMsg() == null || model.getMsg().isEmpty())
            binding.edtMsg.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtMsg.setBackgroundResource(R.drawable.back_editt);
    }
}