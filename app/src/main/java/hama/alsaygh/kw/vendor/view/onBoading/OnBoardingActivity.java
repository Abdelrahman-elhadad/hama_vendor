package hama.alsaygh.kw.vendor.view.onBoading;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.WellComeScreenBinding;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.onBoading.adapter.OnBoardSliderAdapter;

public class OnBoardingActivity extends BaseActivity {
    WellComeScreenBinding binding;
    OnBoardingViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding = WellComeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new OnBoardingViewModel();
        binding.setModel(model);
        model.getOnBoardingObserver().observe(this, onBoardResponse -> {

            if (onBoardResponse.isStatus()) {

                OnBoardSliderAdapter sliderAdapter = new OnBoardSliderAdapter(getLayoutInflater(), this, onBoardResponse.getData());
                binding.viewPager.setAdapter(sliderAdapter);
                binding.pageIndicatorView.setViewPager(binding.viewPager);
            } else {
                Snackbar.make(binding.viewPager, onBoardResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        model.getOnBoarding(this);
        model.animate(this, binding.lineranim);

    }
}
