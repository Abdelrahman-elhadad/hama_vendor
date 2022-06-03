package hama.alsaygh.kw.vendor.view.products;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.product.ProductsResponse;
import hama.alsaygh.kw.vendor.repo.ProductRepo;

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

}
