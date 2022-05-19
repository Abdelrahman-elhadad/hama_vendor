package hama.alsaygh.kw.vendor.view.generalSettings.notification;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.ActivityNotificationSettingsBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class NotificationSettingActivity extends BaseActivity {

    ActivityNotificationSettingsBinding binding;
    NotificationSettingViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new NotificationSettingViewModel(this);
        binding.setModel(model);
        binding.icBackk.setOnClickListener(v -> finish());

        model.getObserver().observe(this, userResponse -> {
            if (!userResponse.isStatus()) {
                if (userResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                } else
                    Snackbar.make(binding.view1, userResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}