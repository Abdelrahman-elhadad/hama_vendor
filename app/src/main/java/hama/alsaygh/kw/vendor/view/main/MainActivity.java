package hama.alsaygh.kw.vendor.view.main;

import android.os.Bundle;

import hama.alsaygh.kw.vendor.databinding.ActivityMainBinding;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    MainActivityViewModel model = new MainActivityViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setModel(model);

        model.getOnBoardingObserver().observe(this, onBoardResponse -> {

            if (onBoardResponse.isStatus()) {
                SharedPreferenceConstant.setSharedPreferenceOnBoarding(MainActivity.this, onBoardResponse.getData());
            }
        });

        model.getOnBoarding(this);
        model.startAnime(binding.seekBar);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.getOnBoardingObserver().removeObservers(this);
    }
}