package hama.alsaygh.kw.vendor.view.generalSettings.language;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import hama.alsaygh.kw.vendor.databinding.ActivityLanguageSettingBinding;
import hama.alsaygh.kw.vendor.utils.ContextUtils;
import hama.alsaygh.kw.vendor.utils.LocalUtils;

public class LanguageActivity extends AppCompatActivity {

    ActivityLanguageSettingBinding binding;
    LanguageViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));
        binding = ActivityLanguageSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new LanguageViewModel(this);
        model.setLightStatusBarAndHide(this);
        binding.setModel(model);
        binding.ivBack.setOnClickListener(v -> finish());

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale localeToSwitchTo = new Locale(LocalUtils.getInstance().getLanguageShort(newBase));
        ContextWrapper localeUpdatedContext = ContextUtils.updateLocale(newBase, localeToSwitchTo);
        Log.i("text__",LocalUtils.getInstance().getLanguageShort(newBase));
        super.attachBaseContext(localeUpdatedContext);

    }
}