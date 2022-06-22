package hama.alsaygh.kw.vendor.model.searchLog;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hama.alsaygh.kw.vendor.model.product.Product;


public class ProductsSearchResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private SearchProductDataStore data;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        if (code == null)
            code = "";
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SearchProductDataStore getData() {
        return data;
    }

    public void setData(SearchProductDataStore data) {
        this.data = data;
    }

    public List<Product> getSProducts() {
        return data.getProducts();
    }
}

class SearchProductDataStore {

    @SerializedName("result")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}