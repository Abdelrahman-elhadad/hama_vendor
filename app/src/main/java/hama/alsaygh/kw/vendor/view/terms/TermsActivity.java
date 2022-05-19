package hama.alsaygh.kw.vendor.view.terms;

import android.os.Bundle;
import android.text.Html;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.ActivityTermAndConditionsBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class TermsActivity extends BaseActivity {
    ActivityTermAndConditionsBinding binding;
    TermsViewModel model;
    Skeleton skeleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTermAndConditionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new TermsViewModel();
        binding.setModel(model);
        binding.imgBack.setOnClickListener(v -> finish());
        skeleton = SkeletonLayoutUtils.createSkeleton(binding.nsv);
        Utils.getInstance().setSkeletonMaskAndShimmer(this, skeleton);
        model.getObserver().observe(this, pageResponse -> {
            skeleton.showOriginal();
            if (pageResponse.isStatus()) {

                model.setTitleMutableLiveData(pageResponse.getData().getTitle());
                model.setUpdatedMutableLiveData(pageResponse.getData().getLast_update());
                model.setDescMutableLiveData(Html.fromHtml(pageResponse.getData().getContent(), Html.FROM_HTML_MODE_LEGACY).toString());
                binding.setModel(model);

            } else {
                if (pageResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                } else
                    Snackbar.make(binding.nsv, pageResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });
        skeleton.showSkeleton();
        model.getTerms(this);
    }
}