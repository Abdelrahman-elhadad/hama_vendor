package hama.alsaygh.kw.vendor.view.auth.mobileVerification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.EnterNewPasswordBinding;
import hama.alsaygh.kw.vendor.databinding.MobileVerificationBinding;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;


public class MobileVerificationActivity extends BaseActivity implements LoginListener {


    private String email;

    MobileVerificationBinding binding;
    VerificationActivityViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MobileVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new VerificationActivityViewModel(this);
        binding.setModel(model);

        if (getIntent() != null)
            email = getIntent().getStringExtra("email");

        model.setEmail(email);
        model.setTimer(binding.timer);
        model.startTimer(binding.timer, binding.textView8);

        binding.backReset.setOnClickListener(v -> finish());

        model.getResendObservable().observe(this, generalResponse -> {
            if (!generalResponse.isStatus())
                Snackbar.make(binding.view, generalResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

        });
        model.getVerifyObservable().observe(this, loginResponse -> {

            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {

                Intent intent = new Intent(MobileVerificationActivity.this, EnterNewPasswordBinding.class);
                intent.putExtra("token", loginResponse.getData().getToken());
                startActivity(intent);
            } else
                Snackbar.make(binding.view, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public void validation() {

        if (model.getUserName() != null && !model.getUserName().isEmpty()) {
            binding.txtPinEntry.setPinBackground(ContextCompat.getDrawable(this, R.drawable.back_reset_pass));
        } else {
            binding.txtPinEntry.setPinBackground(ContextCompat.getDrawable(this, R.drawable.back_reset_pass_red));
        }
    }
}