package hama.alsaygh.kw.vendor.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.databinding.ActivitySearchCategoriesProductBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.repo.RequestWrapper;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.products.ProductsViewModel;
import hama.alsaygh.kw.vendor.view.products.adapter.StoreProductRecycleViewAdapter;
import hama.alsaygh.kw.vendor.view.products.addProduct.AddEditProductActivity;

public class SearchCategoriesProductActivity extends BaseActivity implements OnGeneralClickListener {

    ActivitySearchCategoriesProductBinding binding;
    ProductsViewModel model;
    Skeleton skeleton;
    FragmentManager fragmentManager;

    int page = 1;
    boolean isLast = false, isLoading = false;
    StoreProductRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchCategoriesProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        model = new ProductsViewModel();
        binding.setModel(model);

        binding.imgBack.setOnClickListener(v -> finish());

        if (getIntent() != null) {
            model.setCategory_level_1(getIntent().getIntExtra(AppConstants.CAT_LEVEL_1, -1));
            model.setCategory_level_2(getIntent().getIntExtra(AppConstants.CAT_LEVEL_2, -1));
            model.setCategory_level_3(getIntent().getIntExtra(AppConstants.CAT_LEVEL_3, -1));
        }


        skeleton = SkeletonLayoutUtils.applySkeleton(binding.rvProducts, R.layout.item_rv_my_prouduct, 2);
        Utils.getInstance().setSkeletonMaskAndShimmer(this, skeleton);

        model.getObserver().observe(this, productsResponse -> {
            if (page == 1)
                skeleton.showOriginal();

            isLoading = false;
            binding.swRefresh.setRefreshing(false);
            binding.pbLoading.setVisibility(View.GONE);

            if (productsResponse.isStatus()) {
                if (productsResponse.getData().isEmpty()) {
                    if (page == 1) {
                        binding.rvProducts.setVisibility(View.GONE);
                        binding.llNoProduct.setVisibility(View.VISIBLE);
                    } else
                        binding.llNoProduct.setVisibility(View.GONE);
                    isLast = true;
                } else {
                    binding.rvProducts.setVisibility(View.VISIBLE);
                    if (page == 1) {
                        adapter = new StoreProductRecycleViewAdapter(productsResponse.getData(), fragmentManager, this);
                        binding.rvProducts.setAdapter(adapter);

                    } else {
                        if (adapter != null)
                            adapter.addItems(productsResponse.getData());
                        else {
                            adapter = new StoreProductRecycleViewAdapter(productsResponse.getData(), fragmentManager, this);
                            binding.rvProducts.setAdapter(adapter);
                        }
                    }
                }
            } else {

                if (productsResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(fragmentManager, "login");
                } else {
                    Snackbar.make(binding.rvProducts, productsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        binding.swRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.textviewhome), ContextCompat.getColor(this, R.color.color_navigation));

        binding.swRefresh.setOnRefreshListener(() -> {
            if (MainApplication.isConnected) {

                isLoading = true;
                isLast = false;
                page = 1;
                model.getProducts(this, page);

            } else
                binding.swRefresh.setRefreshing(false);
        });

        binding.nsMain.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = binding.nsMain.getChildAt(binding.nsMain.getChildCount() - 1);

                int diff = (view.getBottom() - (binding.nsMain.getHeight() + binding.nsMain
                        .getScrollY()));

                if (diff == 0) {
                    if (!isLoading && !isLast) {
                        binding.pbLoading.setVisibility(View.VISIBLE);
                        isLoading = true;
                        ++page;
                        model.getProducts(SearchCategoriesProductActivity.this, page);
                    }

                }
            }
        });


        isLoading = true;
        isLast = false;
        page = 1;
        skeleton.showSkeleton();
        model.getProducts(this, page);

//        binding.linerFilter.setOnClickListener(v -> {
//            FilterByDialog.newInstance(model.getType_of_price(), model.getCategory_level_1(), model.getCategory_level_2(),
//                            model.getCategory_level_3(), model.getRange_price_from(), model.getRange_price_to(), ProductsFragment.this)
//                    .show(fragmentManager, "filter");
//        });
//
//        binding.linerSort.setOnClickListener(v -> {
//            SortByDialog.newInstance(model.getSort_key(), this)
//                    .show(fragmentManager, "sort");
//        });

    }

    @Override
    public void onItemClick(Object object, int position) {

    }

    @Override
    public void onEditClick(Object object, int position) {

        Intent intent = new Intent(this, AddEditProductActivity.class);
        intent.putExtra(AppConstants.PRODUCT, RequestWrapper.getInstance().getGson().toJson((Product) object));
        startActivity(intent);

    }

    @Override
    public void onDeleteClick(Object object, int position) {

        if (adapter != null) {
            adapter.removeItem((int) object);
            if (adapter.getItemCount() == 0) {

                binding.rvProducts.setVisibility(View.GONE);
                binding.llNoProduct.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.getObserver().removeObservers(this);
    }

}