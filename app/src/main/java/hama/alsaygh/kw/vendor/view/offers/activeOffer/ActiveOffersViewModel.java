package hama.alsaygh.kw.vendor.view.offers.activeOffer;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.product.ProductsResponse;
import hama.alsaygh.kw.vendor.model.searchLog.ProductsSearchResponse;
import hama.alsaygh.kw.vendor.repo.ProductRepo;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;

public class ActiveOffersViewModel extends ViewModel {

    private final String TAG = "ProductsViewModel";

    private MutableLiveData<ProductsResponse> languageResponseMutableLiveData;
    private MutableLiveData<ProductsSearchResponse> productsSearchResponseMutableLiveData;

    final private ProductRepo productRepo;
    final private ProfileRepo profileRepo;

    private String sort_key = "", type_of_price = "", range_price_from = "", range_price_to = "";
    private int category_level_1 = -1, category_level_2 = -1, category_level_3 = -1;


    public ActiveOffersViewModel() {
        productRepo = new ProductRepo();
        profileRepo = new ProfileRepo();

    }

    public MutableLiveData<ProductsResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public MutableLiveData<ProductsSearchResponse> getSearchObserver() {
        if (productsSearchResponseMutableLiveData == null)
            productsSearchResponseMutableLiveData = new MutableLiveData<>();
        return productsSearchResponseMutableLiveData;
    }

    public void getProducts(Context context, int page) {
        productRepo.getProducts(context, page, sort_key, category_level_1, category_level_2, category_level_3, type_of_price, range_price_from, range_price_to, 1, languageResponseMutableLiveData);
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

    public void getSearchLogProductActive(Context context, String search) {
        profileRepo.getSearchLogsProduct(context, search, 1, productsSearchResponseMutableLiveData);
    }

    public void getSearchLogProductNotActive(Context context, String search) {
        profileRepo.getSearchLogsProduct(context, search, 0, productsSearchResponseMutableLiveData);
    }
}
