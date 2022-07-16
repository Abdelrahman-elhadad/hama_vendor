package hama.alsaygh.kw.vendor.view.order.orderDetails;

import static hama.alsaygh.kw.vendor.view.order.OrdersViewModel.CANCELED;
import static hama.alsaygh.kw.vendor.view.order.OrdersViewModel.PENDING;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.databinding.ActivityOrderDetailsBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.dialog.order.AcceptOrderDialog;
import hama.alsaygh.kw.vendor.dialog.order.DiscardOrderDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.cart.CartItem;
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
        model.setType(status);
        model.setId(id);
        model.getObserver().observe(this, orderResponse -> {

            skeleton.showOriginal();

            if (orderResponse.isStatus()) {
                if (orderResponse.getData() != null) {
                    model.setStoreModel(orderResponse.getData());
                    binding.setModel(model);
                    model.addProductVisibility.set(View.VISIBLE);
                    model.pbAddProductVisibility.set(View.GONE);

                    if (status == PENDING) {
                        boolean isAccepted = true;
                        for (CartItem storeModel : orderResponse.getData().getItems()) {
                            if (!storeModel.getStatus().equalsIgnoreCase("accepted")) {
                                isAccepted = false;
                                break;
                            }
                        }
                        if (isAccepted)
                            binding.llNext.setVisibility(View.GONE);
                        else
                            binding.llNext.setVisibility(View.VISIBLE);
                    } else
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


                    if (model.storeModel.getVoucher_image_link() == null || !model.storeModel.getVoucher_image_link().isEmpty())
                        binding.btnImage.setVisibility(View.GONE);
                    else
                        binding.btnImage.setVisibility(View.VISIBLE);

                    if (model.storeModel.getVoucher_pdf_link() == null || !model.storeModel.getVoucher_pdf_link().isEmpty())
                        binding.btnPdf.setVisibility(View.GONE);
                    else
                        binding.btnPdf.setVisibility(View.VISIBLE);

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


        binding.butAccept.setOnClickListener(v -> {

            if (MainApplication.isConnected) {
                AcceptOrderDialog.newInstance(model.storeModel.getId(), null)
                        .show(getSupportFragmentManager(), "accept");
            } else
                Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

        });

        binding.butDiscard.setOnClickListener(v -> {
            if (MainApplication.isConnected) {
                DiscardOrderDialog.newInstance(model.storeModel.getId(), this)
                        .show(getSupportFragmentManager(), "discard");
            } else
                Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

        });

        binding.btnPdf.setOnClickListener(v ->

        {
            if (MainApplication.isConnected) {
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(model.storeModel.getVoucher_pdf_link()));
                request.setTitle(getString(R.string.download_pdf))
                        .setDescription(getString(R.string.download_file))
                        .setDestinationInExternalFilesDir(this,
                                Environment.DIRECTORY_DOWNLOADS, "hama_order_" + System.currentTimeMillis())
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadManager.enqueue(request);
            } else
                Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

        });

        binding.btnImage.setOnClickListener(v ->
        {
            if (MainApplication.isConnected) {
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(model.storeModel.getVoucher_image_link()));
                request.setTitle(getString(R.string.download_image))
                        .setDescription(getString(R.string.download_file))
                        .setDestinationInExternalFilesDir(this,
                                Environment.DIRECTORY_DOWNLOADS, "hama_order_" + System.currentTimeMillis())
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadManager.enqueue(request);
            } else
                Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onItemClick(Object object, int position) {

    }

    @Override
    public void onEditClick(Object object, int position) {

    }

    @Override
    public void onDeleteClick(Object object, int position) {
        if (MainApplication.isConnected) {
            skeleton.showSkeleton();
            binding.llNext.setVisibility(View.GONE);
            status = CANCELED;
            model.getOrders(this, id, status);
            model.setInternetConnection();
        } else
            model.setNoInternetConnection();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MainApplication.isConnected) {
            skeleton.showSkeleton();
            binding.llNext.setVisibility(View.GONE);
            model.getOrders(this, id, status);
            model.setInternetConnection();
        } else {
            model.setNoInternetConnection();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.getObserver().removeObservers(this);
    }
}