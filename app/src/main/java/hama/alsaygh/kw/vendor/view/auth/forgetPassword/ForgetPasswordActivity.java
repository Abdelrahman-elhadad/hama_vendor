package hama.alsaygh.kw.vendor.view.auth.forgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityForgetPasswordBinding;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.view.auth.mobileVerification.MobileVerificationActivity;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class ForgetPasswordActivity extends BaseActivity implements LoginListener {

    ActivityForgetPasswordBinding binding;
    ForgetPasswordActivityViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ForgetPasswordActivityViewModel(this);
        binding.setModel(model);

        binding.ivBack.setOnClickListener(v -> finish());
        model.getForgetPasswordObservable().observe(this, loginResponse -> {

            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {

                Intent intent = new Intent(ForgetPasswordActivity.this, MobileVerificationActivity.class);
                startActivity(intent);
            } else
                Snackbar.make(binding.editText, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
        });

    }

    @Override
    public void validation() {
        if (model.getUserName() == null || model.getUserName().isEmpty())
            binding.editText.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.editText.setBackgroundResource(R.drawable.back_edit_txt);

    }
}