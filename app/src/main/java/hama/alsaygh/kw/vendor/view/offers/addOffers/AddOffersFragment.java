package hama.alsaygh.kw.vendor.view.offers.addOffers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.databinding.FragmentAddNewOfferBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.dialog.offers.AddEditOfferProductDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.offers.addOffers.adapter.AddOffersRecycleViewAdapter;

public class AddOffersFragment extends BaseFragment implements OnGeneralClickListener {
    FragmentAddNewOfferBinding binding;
    AddOffersViewModel model;

    Skeleton skeleton;
    FragmentManager fragmentManager;

    int page = 1;
    boolean isLast = false, isLoading = false;
    String search = "";
    AddOffersRecycleViewAdapter adapter;

    public static AddOffersFragment newInstance() {

        return new AddOffersFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNewOfferBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        model = new AddOffersViewModel();
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
                        adapter = new AddOffersRecycleViewAdapter(productsResponse.getData(), fragmentManager, AddOffersFragment.this);
                        binding.rvProducts.setAdapter(adapter);

                    } else {
                        if (adapter != null)
                            adapter.addItems(productsResponse.getData());
                        else {
                            adapter = new AddOffersRecycleViewAdapter(productsResponse.getData(), fragmentManager, AddOffersFragment.this);
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
            if (MainApplication.isConnected) {

                isLoading = true;
                isLast = false;
                page = 1;
                model.getProducts(requireContext(), page);
                binding.svOffer.setQuery("", true);
                search = "";

            } else
                binding.swRefresh.setRefreshing(false);
        });

        binding.nsMain.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) binding.nsMain.getChildAt(binding.nsMain.getChildCount() - 1);

                int diff = (view.getBottom() - (binding.nsMain.getHeight() + binding.nsMain
                        .getScrollY()));
                if (search == null || search.isEmpty()) {
                    if (diff == 0) {
                        if (getActivity() != null) {

                            if (!isLoading && !isLast) {
                                binding.pbLoading.setVisibility(View.VISIBLE);
                                isLoading = true;
                                ++page;
                                model.getProducts(requireContext(), page);
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

        isLoading = true;
        isLast = false;
        page = 1;
        skeleton.showSkeleton();
        model.getProducts(requireContext(), page);

        binding.button3.setOnClickListener(v -> {
            AddEditOfferProductDialog.newInstance(model.getProduct(), this).
                    show(fragmentManager, "add_offers");
        });

        binding.svOffer.setOnCloseListener(() -> {
            search = "";
            binding.svOffer.setQuery("", true);
            isLoading = true;
            isLast = false;
            page = 1;
            skeleton.showSkeleton();
            model.getProducts(requireContext(), page);

            return false;
        });

        binding.svOffer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search = query;
                skeleton.showSkeleton();
                model.getSearchLogProductNotActive(requireContext(), search);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText == null || newText.isEmpty()) {
                    search = "";
                    isLoading = true;
                    isLast = false;
                    page = 1;
                    skeleton.showSkeleton();
                    model.getProducts(requireContext(), page);

                }
                return false;
            }
        });

        model.getSearchObserver().observe(requireActivity(), productsSearchResponse -> {
            skeleton.showOriginal();
            if (productsSearchResponse.isStatus()) {

                adapter = new AddOffersRecycleViewAdapter(productsSearchResponse.getSProducts(), fragmentManager, AddOffersFragment.this);
                binding.rvProducts.setAdapter(adapter);

            } else {
                if (productsSearchResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(fragmentManager, "Login");
                else
                    Snackbar.make(binding.svOffer, productsSearchResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onItemClick(Object object, int position) {

        if (object instanceof Product) {
            model.setSelectedProduct((Product) object);
            binding.button3.setVisibility(View.VISIBLE);
        } else if (object instanceof AddProduct) {
            isLoading = true;
            isLast = false;
            page = 1;
            skeleton.showSkeleton();
            model.getProducts(requireContext(), page);
            model.setSelectedProduct(null);
            binding.button3.setVisibility(View.GONE);

        }
    }

    @Override
    public void onEditClick(Object object, int position) {


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
        model.getObserver().removeObservers(requireActivity());
        model.getSearchObserver().removeObservers(requireActivity());
    }
}
