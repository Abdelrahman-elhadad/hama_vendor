package hama.alsaygh.kw.vendor.view.home;

import android.content.Intent;
import android.os.Bundle;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityHomeBinding;
import hama.alsaygh.kw.vendor.fragment.HomeFragment;
import hama.alsaygh.kw.vendor.utils.LocalUtils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "ButtomNavigation";
    public static int position;
    private static String lang;

    ActivityHomeBinding binding;
    private HomeActivityViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new HomeActivityViewModel(getSupportFragmentManager());
        binding.setModel(model);
        binding.buttomNav.setSelectedItemId(R.id.item_home);
        model.commitFragment(new HomeFragment(), model.getHome());
        lang = LocalUtils.getInstance().getLanguageShort(this);
    }

    @Override
    public void onBackPressed() {
        if (position != model.getHome())
            binding.buttomNav.setSelectedItemId(R.id.item_home);
          //  model.commitFragment(new HomeFragment(), model.getHome());
        else
            super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!lang.equalsIgnoreCase(LocalUtils.getInstance().getLanguageShort(this))) {
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
