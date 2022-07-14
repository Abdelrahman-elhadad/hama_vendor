package hama.alsaygh.kw.vendor.view.storeRate;

import android.os.Bundle;
import android.view.View;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityStoreRateBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.utils.image.CircleTransform;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.storeRate.adapter.StoreReviewAdapter;

public class StoreRateActivity extends BaseActivity {

    ActivityStoreRateBinding binding;
    RateStoreViewModel model;
    Skeleton skeleton;

    int page = 1;
    boolean isLast = false, isLoading = false;
    StoreReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreRateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new RateStoreViewModel();
        binding.setModel(model);

        skeleton = SkeletonLayoutUtils.createSkeleton(binding.nsMain);
        Utils.getInstance().setSkeletonMaskAndShimmer(this, skeleton);

        model.getObservable().observe(this, productsResponse -> {
            if (page == 1)
                skeleton.showOriginal();

            isLoading = false;
            binding.pbLoading.setVisibility(View.GONE);

            if (productsResponse.isStatus()) {
                if (productsResponse.getData().isEmpty()) {
                    if (page == 1) {
                        binding.rvReview.setVisibility(View.GONE);
                        binding.llNoProduct.setVisibility(View.VISIBLE);
                    } else
                        binding.llNoProduct.setVisibility(View.GONE);
                    isLast = true;
                } else {
                    binding.rvReview.setVisibility(View.VISIBLE);
                    if (page == 1) {
                        adapter = new StoreReviewAdapter(productsResponse.getData());
                        binding.rvReview.setAdapter(adapter);

                    } else {
                        if (adapter != null)
                            adapter.addItems(productsResponse.getData());
                        else {
                            adapter = new StoreReviewAdapter(productsResponse.getData());
                            binding.rvReview.setAdapter(adapter);
                        }
                    }
                }
            } else {

                if (productsResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                } else {
                    Snackbar.make(binding.rvReview, productsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        binding.nsMain.getViewTreeObserver().addOnScrollChangedListener(() -> {
            View view = binding.nsMain.getChildAt(binding.nsMain.getChildCount() - 1);

            int diff = (view.getBottom() - (binding.nsMain.getHeight() + binding.nsMain
                    .getScrollY()));

            if (diff == 0) {


                if (!isLoading && !isLast) {
                    binding.pbLoading.setVisibility(View.VISIBLE);
                    isLoading = true;
                    ++page;
                    model.getReviews(StoreRateActivity.this, page);
                }

            }
        });


        isLoading = true;
        isLast = false;
        page = 1;
        skeleton.showSkeleton();
        model.getReviews(this, page);

        String imageUrl = SharedPreferenceConstant.getSharedPreferenceUser(this).getLogo();
        if (imageUrl != null && !imageUrl.isEmpty())
            Picasso.get().load(imageUrl).transform(new CircleTransform()).into(binding.ivLogo, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {

                    Picasso.get().load(R.drawable.image_not_foundpng).transform(new CircleTransform()).into(binding.ivLogo);
                }
            });
        else
            Picasso.get().load(R.drawable.image_not_foundpng).transform(new CircleTransform()).into(binding.ivLogo);

        binding.tvStoreName.setText(getName());

        binding.toolbarImg.setOnClickListener(v -> finish());

    }

    public String getName() {
        return getString(R.string.store_name).replace("xxx", SharedPreferenceConstant.getSharedPreferenceUser(this).getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.getObservable().removeObservers(this);
    }
}