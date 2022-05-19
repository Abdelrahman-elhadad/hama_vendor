package hama.alsaygh.kw.vendor.view.contactUs;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityContactUsBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class ContactUsActivity extends BaseActivity implements LoginListener {
    ActivityContactUsBinding binding;
    ContactUsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ContactUsViewModel(this);
        binding.setModel(model);

        binding.imgBack.setOnClickListener(v -> finish());
        model.getForgetPasswordObservable().observe(this, loginResponse -> {

            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {

                binding.edtEmail.setText("");
                binding.edtName.setText("");
                binding.edtMsg.setText("");

                Snackbar.make(binding.view19, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            } else {
                if (loginResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.view19, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        model.getSocialObservable().observe(this, loginResponse -> {

            if (loginResponse.isStatus()) {
                model.setSocialMedia(loginResponse.getData());
                binding.setModel(model);
            }
        });

        model.getSocialMedia(this);
    }

    @Override
    public void validation() {
        if (model.getName() == null || model.getName().isEmpty())
            binding.edtName.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtName.setBackgroundResource(R.drawable.back_editt);

        if (model.getEmail() == null || model.getEmail().isEmpty())
            binding.edtEmail.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtEmail.setBackgroundResource(R.drawable.back_editt);

        if (model.getMsg() == null || model.getMsg().isEmpty())
            binding.edtMsg.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.edtMsg.setBackgroundResource(R.drawable.back_editt);
    }
}