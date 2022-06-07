package hama.alsaygh.kw.vendor.view.products.addProduct;

import android.os.Bundle;

import hama.alsaygh.kw.vendor.databinding.ActivityAddProductBinding;
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
    }
}