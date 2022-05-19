package hama.alsaygh.kw.vendor.view.generalSettings;

import android.content.Intent;
import android.os.Bundle;

import hama.alsaygh.kw.vendor.databinding.ActivityGeneralSettingsBinding;
import hama.alsaygh.kw.vendor.utils.LocalUtils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class GeneralSettingsActivity extends BaseActivity {

    ActivityGeneralSettingsBinding binding;
    GeneralSettingViewModel model;
    private static String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGeneralSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new GeneralSettingViewModel();
        binding.setModel(model);
        binding.ivBack.setOnClickListener(v -> finish());
        lang = LocalUtils.getInstance().getLanguageShort(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!lang.equalsIgnoreCase(LocalUtils.getInstance().getLanguageShort(this))) {
            finish();
            Intent intent = new Intent(this, GeneralSettingsActivity.class);
            startActivity(intent);
        }
    }
}