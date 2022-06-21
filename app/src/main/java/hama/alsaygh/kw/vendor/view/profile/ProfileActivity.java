package hama.alsaygh.kw.vendor.view.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.ActivityProfileBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class ProfileActivity extends BaseActivity {

    ActivityProfileBinding binding;
    EditProfileViewModel model;
    Skeleton skeleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new EditProfileViewModel(this);
        binding.setModel(model);
        binding.imgBack.setOnClickListener(v -> finish());
        skeleton = SkeletonLayoutUtils.createSkeleton(binding.nsMain);
        Utils.getInstance().setSkeletonMaskAndShimmer(this, skeleton);


        model.getProfileObserver().observe(this, loginResponse -> {

            skeleton.showOriginal();
            if (loginResponse.isStatus()) {

                model.setUser(loginResponse.getData().getDelegate());
                binding.setModel(model);

            } else {
                if (loginResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.nsMain, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });


        skeleton.showSkeleton();
        model.getProfile(this);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, model.arrayForSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spGender.setAdapter(arrayAdapter);
        binding.spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    model.getUser().setGender("0");
                } else {
                    model.getUser().setGender("1");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (model.getUser().getGender().equalsIgnoreCase("0")) {
            binding.spGender.setSelection(1);
        } else if (model.getUser().getGender().equalsIgnoreCase("1")) {
            binding.spGender.setSelection(0);
        }

    }
}