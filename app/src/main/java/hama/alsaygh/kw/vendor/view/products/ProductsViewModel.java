package hama.alsaygh.kw.vendor.view.products;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.model.product.ProductsResponse;
import hama.alsaygh.kw.vendor.repo.ProductRepo;
import hama.alsaygh.kw.vendor.view.products.addProduct.AddEditProductActivity;

public class ProductsViewModel extends ViewModel {

    private final String TAG = "ProductsViewModel";

    private MutableLiveData<ProductsResponse> languageResponseMutableLiveData;

    final private ProductRepo productRepo;

    private String sort_key = "", type_of_price = "", range_price_from = "", range_price_to = "";
    private int category_level_1 = -1, category_level_2 = -1, category_level_3 = -1;


    public ProductsViewModel() {
        productRepo = new ProductRepo();

    }

    public MutableLiveData<ProductsResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void getProducts(Context context, int page) {
        productRepo.getProducts(context, page, sort_key, category_level_1, category_level_2, category_level_3, type_of_price, range_price_from, range_price_to, -1, languageResponseMutableLiveData);
    }


    public void onAddProductClick(View v) {
        if (MainApplication.isConnected) {
            Intent intent = new Intent(v.getContext(), AddEditProductActivity.class);
            v.getContext().startActivity(intent);
        } else {
            Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();
        }
    }


    public String getSort_key() {
        return sort_key;
    }

    public void setSort_key(String sort_key) {
        this.sort_key = sort_key;
    }

    public String getType_of_price() {
        return type_of_price;
    }

    public void setType_of_price(String type_of_price) {
        this.type_of_price = type_of_price;
    }

    public String getRange_price_from() {
        return range_price_from;
    }

    public void setRange_price_from(String range_price_from) {
        this.range_price_from = range_price_from;
    }

    public String getRange_price_to() {
        return range_price_to;
    }

    public void setRange_price_to(String range_price_to) {
        this.range_price_to = range_price_to;
    }

    public int getCategory_level_1() {
        return category_level_1;
    }

    public void setCategory_level_1(int category_level_1) {
        this.category_level_1 = category_level_1;
    }

    public int getCategory_level_2() {
        return category_level_2;
    }

    public void setCategory_level_2(int category_level_2) {
        this.category_level_2 = category_level_2;
    }

    public int getCategory_level_3() {
        return category_level_3;
    }

    public void setCategory_level_3(int category_level_3) {
        this.category_level_3 = category_level_3;
    }


    private final ObservableInt isConnected = new ObservableInt();
    private final ObservableInt isNotConnectedView = new ObservableInt();

    public void setInternetConnection() {
        isConnected.set(View.VISIBLE);
        isNotConnectedView.set(View.GONE);
    }

    public void setNoInternetConnection() {
        isConnected.set(View.GONE);
        isNotConnectedView.set(View.VISIBLE);
    }

    public ObservableInt getIsConnected() {
        return isConnected;
    }

    public ObservableInt getIsNotConnectedView() {
        return isNotConnectedView;
    }

    public void onTryAgainClick(View view) {
        if (MainApplication.isConnected) {
            setInternetConnection();
            getProducts(view.getContext(), 1);
        } else {
            setNoInternetConnection();
        }
    }

}
