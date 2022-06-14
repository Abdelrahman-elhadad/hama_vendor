package hama.alsaygh.kw.vendor.view.order.orderDetails;

import static hama.alsaygh.kw.vendor.view.order.OrdersViewModel.PENDING;

import android.os.Bundle;
import android.view.View;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.ActivityOrderDetailsBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.dialog.order.AcceptOrderDialog;
import hama.alsaygh.kw.vendor.dialog.order.DiscardOrderDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.order.orderDetails.adapter.OrderInvoiceProductsRecycleViewAdapter;
import hama.alsaygh.kw.vendor.view.order.orderDetails.adapter.OrderProductsRecycleViewAdapter;

public class OrderDetailsActivity extends BaseActivity implements OnGeneralClickListener {

    ActivityOrderDetailsBinding binding;
    OrderDetailsViewModel model;
    Skeleton skeleton;
    int id, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new OrderDetailsViewModel(this);
        binding.setModel(model);
        skeleton = SkeletonLayoutUtils.createSkeleton(binding.nsvMain);
        Utils.getInstance().setSkeletonMaskAndShimmer(this, skeleton);
        binding.toolbarImg.setOnClickListener(v -> finish());
        if (getIntent() != null) {
            id = getIntent().getIntExtra(AppConstants.ORDER_ID, -1);
            status = getIntent().getIntExtra(AppConstants.ORDER_STATUS, -1);
        }
        model.getObserver().observe(this, orderResponse -> {

            skeleton.showOriginal();

            if (orderResponse.isStatus()) {
                if (orderResponse.getData() != null) {
                    model.setStoreModel(orderResponse.getData());
                    binding.setModel(model);
                    model.addProductVisibility.set(View.VISIBLE);
                    model.pbAddProductVisibility.set(View.GONE);

                    if (status == PENDING)
                        binding.llNext.setVisibility(View.VISIBLE);
                    else
                        binding.llNext.setVisibility(View.GONE);

                    if (model.storeModel.getDelivery_type().equalsIgnoreCase("hand_by_hand")) {
                        binding.tvCountry.setVisibility(View.GONE);
                        binding.tvCountryName.setVisibility(View.GONE);
                        binding.tvCity.setVisibility(View.GONE);
                        binding.tvBuilding.setVisibility(View.GONE);
                        binding.tvBuildingNo.setVisibility(View.GONE);
                        binding.tvCity.setVisibility(View.GONE);
                        binding.tvZipCode.setVisibility(View.GONE);
                        binding.tvZipCodeTitle.setVisibility(View.GONE);
                        binding.llAddress.setVisibility(View.GONE);
                        binding.tvDeliveryDateTitle.setVisibility(View.VISIBLE);
                        binding.tvDeliveryDate.setVisibility(View.VISIBLE);
                        binding.llDate.setVisibility(View.VISIBLE);

                    } else {
                        binding.tvCountry.setVisibility(View.VISIBLE);
                        binding.tvCountryName.setVisibility(View.VISIBLE);
                        binding.tvCity.setVisibility(View.VISIBLE);
                        binding.tvBuilding.setVisibility(View.VISIBLE);
                        binding.tvBuildingNo.setVisibility(View.VISIBLE);
                        binding.tvCity.setVisibility(View.VISIBLE);
                        binding.tvZipCode.setVisibility(View.VISIBLE);
                        binding.tvZipCodeTitle.setVisibility(View.VISIBLE);
                        binding.llAddress.setVisibility(View.VISIBLE);
                        binding.tvDeliveryDateTitle.setVisibility(View.GONE);
                        binding.tvDeliveryDate.setVisibility(View.GONE);
                        binding.llDate.setVisibility(View.GONE);
                    }

                    OrderProductsRecycleViewAdapter adapter = new OrderProductsRecycleViewAdapter(orderResponse.getData().getItems(), status, OrderDetailsActivity.this);
                    binding.rvProducts.setAdapter(adapter);

                    OrderInvoiceProductsRecycleViewAdapter invoiceAdapter = new OrderInvoiceProductsRecycleViewAdapter(orderResponse.getData().getItems(), status);
                    binding.rvInvoiceProduct.setAdapter(invoiceAdapter);
                }

            } else {
                if (orderResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.nsvMain, orderResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

        });


        binding.butAccept.setOnClickListener(v ->
                AcceptOrderDialog.newInstance(model.storeModel.getId(), null)
                        .show(getSupportFragmentManager(), "accept")
        );

        binding.butDiscard.setOnClickListener(v ->
                DiscardOrderDialog.newInstance(model.storeModel.getId(), this)
                        .show(getSupportFragmentManager(), "discard")
        );

    }

    @Override
    public void onItemClick(Object object, int position) {

    }

    @Override
    public void onEditClick(Object object, int position) {

    }

    @Override
    public void onDeleteClick(Object object, int position) {
        skeleton.showSkeleton();
        binding.llNext.setVisibility(View.GONE);
        model.getOrders(this, id, status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        skeleton.showSkeleton();
        binding.llNext.setVisibility(View.GONE);
        model.getOrders(this, id, status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.getObserver().removeObservers(this);
    }
}