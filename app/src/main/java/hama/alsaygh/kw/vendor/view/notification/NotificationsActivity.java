package hama.alsaygh.kw.vendor.view.notification;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityNotificationsBinding;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.notification.adapter.notification.NotificationsAdapter;

public class NotificationsActivity extends BaseActivity {

    private int page = 1;
    private boolean isLast = false;
    private boolean isLoading = false;
    private Skeleton skeleton;
    ActivityNotificationsBinding binding;
    NotificationsViewModel model;
    NotificationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new NotificationsViewModel(this);
        binding.setModel(model);

        binding.mScrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            View view13 = binding.mScrollView.getChildAt(binding.mScrollView.getChildCount() - 1);

            int diff = (view13.getBottom() - (binding.mScrollView.getHeight() + binding.mScrollView.getScrollY()));

            if (diff == 0) {

                if (!isLast && !isLoading) {
                    isLoading = true;
                    binding.pbLoading.setVisibility(View.VISIBLE);
                    page++;
                    model.getNotification(page);
                }
            }
        });
        binding.imgBack.setOnClickListener(v -> {
            finish();
        });

        binding.rvNotification.setLayoutManager(new LinearLayoutManager(this));
        skeleton = SkeletonLayoutUtils.applySkeleton(binding.rvNotification, R.layout.card_notification1, 2);

        model.getNotificationsObservable().observe(this, notificationsResponse -> {
            isLoading = false;
            binding.pbLoading.setVisibility(View.GONE);

            if (notificationsResponse.isStatus()) {
                if (!notificationsResponse.getData().isEmpty()) {

                    if (page == 1) {
                        skeleton.showOriginal();
                        adapter = new NotificationsAdapter(notificationsResponse.getData());
                        binding.rvNotification.setAdapter(adapter);

                    } else {
                        if (adapter != null)
                            adapter.addItem(notificationsResponse.getData());
                    }
                    isLast = notificationsResponse.getData().size() < notificationsResponse.getPagination().getPer_page();
                } else {
                    isLast = true;
                    if (page == 1) {
                        skeleton.showOriginal();
                    }
                }
            } else {
                Snackbar.make(binding.rvNotification, notificationsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });


        isLoading = true;
        skeleton.showSkeleton();
        model.getNotification(page);


    }
}