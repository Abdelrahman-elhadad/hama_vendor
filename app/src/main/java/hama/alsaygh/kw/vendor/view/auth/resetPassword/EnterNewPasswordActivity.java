package hama.alsaygh.kw.vendor.view.auth.resetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityEnterNewPasswordBinding;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.view.auth.login.LoginActivity;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class EnterNewPasswordActivity extends BaseActivity implements LoginListener {
    String token;
    ActivityEnterNewPasswordBinding binding;
    EnterNewPasswordViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityEnterNewPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new EnterNewPasswordViewModel(this);
        binding.setModel(model);

        if (getIntent() != null)
            token = getIntent().getStringExtra("token");

        model.setToken(token);
        binding.backEnterNewPassword.setOnClickListener(v -> finish());

        model.getForgetPasswordObservable().observe(this,generalResponse -> {
            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (generalResponse.isStatus()) {

                Intent intent = new Intent(EnterNewPasswordActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else
                Snackbar.make(binding.textView, generalResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

        });



    }


    @Override
    public void validation() {
        if (binding.editText2.getEditableText().toString().isEmpty()) {
            binding.editText2.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else {
            binding.editText2.setBackgroundResource(R.drawable.back_ground_edit_text);
        }

        if (binding.editText22.getEditableText().toString().isEmpty()) {
            binding.editText22.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else {

            String password = binding.editText2.getEditableText().toString();
            String co_password = binding.editText22.getEditableText().toString();

            if (!password.isEmpty() && !co_password.isEmpty() && !password.equals(co_password)) {
                binding.editText22.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else {
                binding.editText22.setBackgroundResource(R.drawable.back_ground_edit_text);
            }
        }

    }
}