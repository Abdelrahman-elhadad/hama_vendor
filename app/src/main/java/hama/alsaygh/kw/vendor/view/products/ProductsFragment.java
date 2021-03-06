package hama.alsaygh.kw.vendor.view.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.databinding.FragmentProuductsBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.dialog.filterBy.FilterByDialog;
import hama.alsaygh.kw.vendor.dialog.sortBy.SortByDialog;
import hama.alsaygh.kw.vendor.listener.OnFilterListener;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.repo.RequestWrapper;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.products.adapter.StoreProductRecycleViewAdapter;
import hama.alsaygh.kw.vendor.view.products.addProduct.AddEditProductActivity;

public class ProductsFragment extends BaseFragment implements OnGeneralClickListener, OnFilterListener {

    FragmentProuductsBinding binding;
    ProductsViewModel model;
    Skeleton skeleton;
    FragmentManager fragmentManager;

    int page = 1;
    boolean isLast = false, isLoading = false;
    StoreProductRecycleViewAdapter adapter;

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProuductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        model = new ProductsViewModel();
        binding.setModel(model);
        skeleton = SkeletonLayoutUtils.applySkeleton(binding.rvProducts, R.layout.item_rv_my_prouduct, 2);
        Utils.getInstance().setSkeletonMaskAndShimmer(requireContext(), skeleton);

        model.getObserver().observe(requireActivity(), productsResponse -> {
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
                        adapter = new StoreProductRecycleViewAdapter(productsResponse.getData(), fragmentManager, ProductsFragment.this);
                        binding.rvProducts.setAdapter(adapter);

                    } else {
                        if (adapter != null)
                            adapter.addItems(productsResponse.getData());
                        else {
                            adapter = new StoreProductRecycleViewAdapter(productsResponse.getData(), fragmentManager, ProductsFragment.this);
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

        binding.swRefresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.textviewhome), ContextCompat.getColor(requireContext(), R.color.color_navigation));

        binding.swRefresh.setOnRefreshListener(() -> {
            isLast = false;
            page = 1;
            if (MainApplication.isConnected) {

                isLoading = true;

                model.getProducts(requireContext(), page);
                model.setInternetConnection();

            } else {
                isLoading = false;
                binding.swRefresh.setRefreshing(false);
                model.setNoInternetConnection();
            }
        });

        binding.nsMain.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) binding.nsMain.getChildAt(binding.nsMain.getChildCount() - 1);

                int diff = (view.getBottom() - (binding.nsMain.getHeight() + binding.nsMain
                        .getScrollY()));

                if (diff == 0) {
                    if (getActivity() != null) {

                        if (!isLoading && !isLast) {
                            if (MainApplication.isConnected) {
                                binding.pbLoading.setVisibility(View.VISIBLE);
                                isLoading = true;
                                ++page;
                                model.getProducts(requireContext(), page);
                                model.setInternetConnection();
                            } else {
                                model.setNoInternetConnection();
                                page = 1;
                                isLoading = false;
                                isLast = false;
                            }
                        }
                    }
                }
//                if (binding.nsMain.getScrollY() == 0) {
//                    binding.rlToTop.setVisibility(View.GONE);
//                } else
//                    binding.rlToTop.setVisibility(View.VISIBLE);
            }
        });


        getProducts();

        binding.linerFilter.setOnClickListener(v -> {
            if (MainApplication.isConnected) {
                FilterByDialog.newInstance(model.getType_of_price(), model.getCategory_level_1(), model.getCategory_level_2(),
                                model.getCategory_level_3(), model.getRange_price_from(), model.getRange_price_to(), ProductsFragment.this)
                        .show(fragmentManager, "filter");
            } else
                Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

        });

        binding.linerSort.setOnClickListener(v -> {
            if (MainApplication.isConnected) {
                SortByDialog.newInstance(model.getSort_key(), ProductsFragment.this)
                        .show(fragmentManager, "sort");
            } else
                Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

        });

    }

    @Override
    public void onItemClick(Object object, int position) {

    }

    @Override
    public void onEditClick(Object object, int position) {
        if (MainApplication.isConnected) {
            Intent intent = new Intent(requireContext(), AddEditProductActivity.class);
            intent.putExtra(AppConstants.PRODUCT, RequestWrapper.getInstance().getGson().toJson((Product) object));
            startActivity(intent);
        } else
            Snackbar.make(binding.rvProducts, requireContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();


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
    public void onFilterClick(String type_of_price, int category_level_1, int category_level_2, int category_level_3, String range_price_from, String range_price_to) {

        model.setType_of_price(type_of_price);
        model.setCategory_level_1(category_level_1);
        model.setCategory_level_2(category_level_2);
        model.setCategory_level_3(category_level_3);
        model.setRange_price_from(range_price_from);
        model.setRange_price_to(range_price_to);


        getProducts();
    }

    public void getProducts() {
        isLast = false;
        page = 1;
        if (MainApplication.isConnected) {
            isLoading = true;
            skeleton.showSkeleton();
            model.getProducts(requireContext(), page);

            model.setInternetConnection();
        } else {
            isLoading = false;
            model.setNoInternetConnection();
        }
    }

    @Override
    public void onSortClickClick(String sortBy) {

        model.setSort_key(sortBy);
        getProducts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.getObserver().removeObservers(requireActivity());
    }
}