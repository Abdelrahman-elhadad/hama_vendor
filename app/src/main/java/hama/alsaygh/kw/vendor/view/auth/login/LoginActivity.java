package hama.alsaygh.kw.vendor.view.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.LoginBinding;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.home.HomeActivity;
import hama.alsaygh.kw.vendor.view.onBoading.OnBoardingActivity;

public class LoginActivity extends BaseActivity implements LoginListener {
    LoginBinding binding;
    LoginActivityViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model=new LoginActivityViewModel(this);
        binding.setModel(model);
       binding.imgBackLogin.setOnClickListener(v -> {
           Intent i = new Intent(getApplicationContext(), OnBoardingActivity.class);
           startActivity(i);
       });
        model.getLoginObservable().observe(this, loginResponse -> {

            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {
                SharedPreferenceConstant.setSharedPreferenceUserToken(LoginActivity.this,loginResponse.getData().getToken());
                SharedPreferenceConstant.setSharedPreferenceUser(LoginActivity.this,loginResponse.getData().getDelegate());

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else
                Snackbar.make(binding.editText, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public void validation() {
        if (model.getPassword() == null || model.getPassword().isEmpty())
            binding.editPassword.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.editPassword.setBackgroundResource(R.drawable.back_edit_txt);

        if (model.getUserName() == null || model.getUserName().isEmpty())
            binding.editText.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.editText.setBackgroundResource(R.drawable.back_edit_txt);

    }
}
