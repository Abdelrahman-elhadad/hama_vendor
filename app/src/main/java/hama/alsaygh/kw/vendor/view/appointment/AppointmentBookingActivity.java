package hama.alsaygh.kw.vendor.view.appointment;

import android.os.Bundle;
import android.view.View;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.ActivityAppointmentBookingBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class AppointmentBookingActivity extends BaseActivity {

    ActivityAppointmentBookingBinding binding;
    AppointmentViewModel model;
    Skeleton skeleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppointmentBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new AppointmentViewModel();
        binding.setModel(model);
        binding.imgBack.setOnClickListener(v -> finish());
        skeleton = SkeletonLayoutUtils.createSkeleton(binding.llMain);
        Utils.getInstance().setSkeletonMaskAndShimmer(this, skeleton);
        model.getObserver().observe(this, pageResponse -> {
            skeleton.showOriginal();
            if (pageResponse.isStatus()) {

                model.setAppointment(pageResponse.getData());
                binding.setModel(model);

            } else {
                if (pageResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                } else
                    Snackbar.make(binding.llMain, pageResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
        skeleton.showSkeleton();
        model.getAppointment(this);


        model.getContactUsObserver().observe(this, loginResponse -> {

            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {
                Snackbar.make(binding.llMain, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

                binding.editMsg.setText("");
            } else {
                if (loginResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.llMain, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}