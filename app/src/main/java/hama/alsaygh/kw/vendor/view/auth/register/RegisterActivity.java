package hama.alsaygh.kw.vendor.view.auth.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.RegisterBinding;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.home.HomeActivity;

public class RegisterActivity extends BaseActivity implements LoginListener {

    RegisterBinding binding;
    RegisterActivityViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = RegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new RegisterActivityViewModel(this);
        binding.setModel(model);

        binding.imgBackRegister.setOnClickListener(v -> {
            finish();
        });

        model.getRegisterObservable().observe(this, loginResponse -> {
            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {
                SharedPreferenceConstant.setSharedPreferenceUserToken(RegisterActivity.this, loginResponse.getData().getToken());
                SharedPreferenceConstant.setSharedPreferenceUser(RegisterActivity.this, loginResponse.getData().getDelegate());

                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else
                Snackbar.make(binding.edtEmail, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public void validation() {


        if (model.getUserName() == null || model.getUserName().isEmpty())
            binding.edtName.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtName.setBackgroundResource(R.drawable.back_edit_txt);


        if (model.getEmail() == null || model.getEmail().isEmpty())
            binding.edtEmail.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtEmail.setBackgroundResource(R.drawable.back_edit_txt);

        if (model.getPhone() == null || model.getPhone().isEmpty())
            binding.edtPhone.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtPhone.setBackgroundResource(R.drawable.back_edit_txt);

        if (model.getPassword() == null || model.getPassword().isEmpty())
            binding.edtPassword.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtPassword.setBackgroundResource(R.drawable.back_edit_txt);

        if (model.getConfirmPassword() == null || model.getConfirmPassword().isEmpty())
            binding.edtConfirmPassword.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtConfirmPassword.setBackgroundResource(R.drawable.back_edit_txt);

        if ((model.getConfirmPassword() != null && !model.getConfirmPassword().isEmpty()) && (model.getPassword() != null && !model.getPassword().isEmpty()))
            if (!model.getPassword().equals(model.getConfirmPassword()))
                binding.edtConfirmPassword.setBackgroundResource(R.drawable.back_edit_txt_red);
            else
                binding.edtConfirmPassword.setBackgroundResource(R.drawable.back_edit_txt);

    }
}
