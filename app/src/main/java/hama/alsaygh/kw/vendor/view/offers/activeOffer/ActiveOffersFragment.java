package hama.alsaygh.kw.vendor.view.offers.activeOffer;

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
import hama.alsaygh.kw.vendor.databinding.FragmentActiveOffersBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.offers.activeOffer.adapter.ActiveOffersRecycleViewAdapter;

public class ActiveOffersFragment extends BaseFragment implements OnGeneralClickListener {
    FragmentActiveOffersBinding binding;
    ActiveOffersViewModel model;

    Skeleton skeleton;
    FragmentManager fragmentManager;

    int page = 1;
    boolean isLast = false, isLoading = false;
    ActiveOffersRecycleViewAdapter adapter;

    public static ActiveOffersFragment newInstance() {

        return new ActiveOffersFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentActiveOffersBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        model = new ActiveOffersViewModel();
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
                        adapter = new ActiveOffersRecycleViewAdapter(productsResponse.getData(), fragmentManager, ActiveOffersFragment.this);
                        binding.rvProducts.setAdapter(adapter);

                    } else {
                        if (adapter != null)
                            adapter.addItems(productsResponse.getData());
                        else {
                            adapter = new ActiveOffersRecycleViewAdapter(productsResponse.getData(), fragmentManager, ActiveOffersFragment.this);
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


            } else
                binding.swRefresh.setRefreshing(false);
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
                            binding.pbLoading.setVisibility(View.VISIBLE);
                            isLoading = true;
                            ++page;
                            model.getProducts(requireContext(), page);
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


    }

    @Override
    public void onItemClick(Object object, int position) {

    }

    @Override
    public void onEditClick(Object object, int position) {

        if (adapter != null) {
            adapter.editItem((Product) object);
            if (adapter.getItemCount() == 0) {
                binding.rvProducts.setVisibility(View.GONE);
                binding.llNoProduct.setVisibility(View.VISIBLE);
            }
        }
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
    }
}
