package hama.alsaygh.kw.vendor.view.generalSettings.changePassword;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityChangePasswordBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class ChangePasswordActivity extends BaseActivity implements LoginListener {

    ActivityChangePasswordBinding binding;
    ChangePasswordViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ChangePasswordViewModel(this);
        binding.setModel(model);

        binding.icBackk.setOnClickListener(v -> finish());

        model.getChangePasswordObservable().observe(this, generalResponse -> {
            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (generalResponse.isStatus()) {
                SharedPreferenceConstant.setSharedPreferenceUser(ChangePasswordActivity.this, generalResponse.getData());
                Snackbar.make(binding.textView, generalResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            } else {
                if (generalResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                } else
                    Snackbar.make(binding.textView, generalResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void validation() {

        if (model.getOldPassword() == null || model.getOldPassword().isEmpty()) {
            binding.edtOld.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else {
            binding.edtOld.setBackgroundResource(R.drawable.back_ground_edit_text);
        }
        if (model.getPassword() == null || model.getPassword().isEmpty()) {
            binding.edtNew.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else {
            binding.edtNew.setBackgroundResource(R.drawable.back_ground_edit_text);
        }

        if (model.getConfirmPassword() == null || model.getConfirmPassword().isEmpty()) {
            binding.edtConfirmNew.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else {

            String password = model.getPassword();
            String co_password = model.getConfirmPassword();

            if (!password.isEmpty() && !co_password.isEmpty() && !password.equals(co_password)) {
                binding.edtConfirmNew.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else {
                binding.edtConfirmNew.setBackgroundResource(R.drawable.back_ground_edit_text);
            }
        }
    }
}