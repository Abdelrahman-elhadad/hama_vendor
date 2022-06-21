package hama.alsaygh.kw.vendor.view.products.addProduct;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityAddProductBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.repo.RequestWrapper;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class AddEditProductActivity extends BaseActivity {

    ActivityAddProductBinding binding;
    AddEditProductViewModel model;

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent() != null) {
            String json = getIntent().getStringExtra(AppConstants.PRODUCT);
            if (json != null && !json.isEmpty()) {
                Log.i("json", json);
                product = RequestWrapper.getInstance().getGson().fromJson(json, Product.class);
            }
        }

        if (product == null) {
            model = new AddEditProductViewModel(this, getSupportFragmentManager());
            binding.butAddProduct.setText(getString(R.string.add_product));
            binding.home1.setText(getString(R.string.addd));
        } else {
            model = new AddEditProductViewModel(this, product, getSupportFragmentManager());
            binding.butAddProduct.setText(getString(R.string.save_changes));
            binding.home1.setText(getString(R.string.edit_product));
        }
        binding.setModel(model);
        model.commitFragment(AddEditProductStep1Fragment.newInstance(model), model.Step1);

        binding.toolbarImg.setOnClickListener(v -> onBackPressed());


        model.getAddProductObserver().observe(this, productsResponse -> {

            model.pbAddProductVisibility.set(View.GONE);
            model.addProductVisibility.set(View.VISIBLE);
            if (productsResponse.isStatus()) {
                Snackbar.make(binding.llNext, productsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                finish();
            } else {
                if (productsResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.llNext, productsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (model.position == model.Step2)
            model.commitFragment(AddEditProductStep1Fragment.newInstance(model), model.Step1);
        else
            super.onBackPressed();
    }
}