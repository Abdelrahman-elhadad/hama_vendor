package hama.alsaygh.kw.vendor.view.products;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.product.ProductsResponse;
import hama.alsaygh.kw.vendor.repo.ProductRepo;
import hama.alsaygh.kw.vendor.view.products.addProduct.AddEditProductActivity;

public class ProductsViewModel extends ViewModel {

    private final String TAG = "ProductsViewModel";

    private MutableLiveData<ProductsResponse> languageResponseMutableLiveData;

    final private ProductRepo productRepo;

    public ProductsViewModel() {
        productRepo = new ProductRepo();

    }

    public MutableLiveData<ProductsResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void getProducts(Context context, int page) {
        productRepo.getProducts(context, page, languageResponseMutableLiveData);
    }


    public void onAddProductClick(View v) {
        Intent intent = new Intent(v.getContext(), AddEditProductActivity.class);
        v.getContext().startActivity(intent);
    }
}
