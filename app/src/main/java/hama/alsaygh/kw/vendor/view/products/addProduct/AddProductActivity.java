package hama.alsaygh.kw.vendor.view.products.addProduct;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.ActivityAddProductBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class AddProductActivity extends BaseActivity {

    ActivityAddProductBinding binding;
    AddProductViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new AddProductViewModel(this, getSupportFragmentManager());
        binding.setModel(model);
        model.commitFragment(AddProductStep1Fragment.newInstance(model), model.Step1);

        binding.toolbarImg.setOnClickListener(v -> finish());


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
        if (model.position != model.Step2)
            model.commitFragment(AddProductStep1Fragment.newInstance(model), model.Step1);
        else
            super.onBackPressed();
    }
}