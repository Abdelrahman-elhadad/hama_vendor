package hama.alsaygh.kw.vendor.view.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivitySearchResultProductBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.repo.RequestWrapper;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.products.adapter.StoreProductRecycleViewAdapter;
import hama.alsaygh.kw.vendor.view.products.addProduct.AddEditProductActivity;

public class SearchResultProductActivity extends BaseActivity implements OnGeneralClickListener {
    ActivitySearchResultProductBinding binding;
    SearchViewModel model;

    String search;
    private Skeleton skeleton;
    StoreProductRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new SearchViewModel(this);
        binding.setModel(model);
        binding.imgBack.setOnClickListener(v -> finish());

        binding.recycleSearchForStores.setLayoutManager(new GridLayoutManager(this, 2));

        if (getIntent() != null) {
            search = getIntent().getStringExtra(AppConstants.SEARCH);
        }

        skeleton = SkeletonLayoutUtils.applySkeleton(binding.recycleSearchForStores, R.layout.item_rv_my_prouduct, 3);
        Utils.getInstance().setSkeletonMaskAndShimmer(this, skeleton);

        if (search != null && !search.isEmpty()) {
            skeleton.showSkeleton();
            model.getSearchLogProduct(this, search);
        }


        binding.editSerchForStores.setOnCloseListener(() -> {
            search = "";
            binding.editSerchForStores.setQuery("", true);
            return false;
        });


        binding.editSerchForStores.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search = query;
                skeleton.showSkeleton();
                model.getSearchLogProduct(SearchResultProductActivity.this, search);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        model.getSearchProductObserver().observe(this, productsSearchResponse -> {
            skeleton.showOriginal();
            if (productsSearchResponse.isStatus()) {

                adapter = new StoreProductRecycleViewAdapter(productsSearchResponse.getSProducts(), getSupportFragmentManager(), this);
                binding.recycleSearchForStores.setAdapter(adapter);


            } else {
                if (productsSearchResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "Login");
                else
                    Snackbar.make(binding.editSerchForStores, productsSearchResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.getSearchProductObserver().removeObservers(this);
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
        }
    }
}