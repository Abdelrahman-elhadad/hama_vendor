package hama.alsaygh.kw.vendor.model.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hama.alsaygh.kw.vendor.model.mySales.Statistics;
import hama.alsaygh.kw.vendor.model.product.Product;

public class HomeData {


    @SerializedName("statistics")
    private Statistics statistics;

    @SerializedName("best_seller_days")
    private BestSellerDaysData best_seller_days;

    @SerializedName("store_info")
    private HomeStoreInfoData store_info;

    @SerializedName("best_products")
    private List<Product> best_products;

    @SerializedName("user_rate")
    private List<HomeData> user_rate;

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public BestSellerDaysData getBest_seller_days() {
        return best_seller_days;
    }

    public void setBest_seller_days(BestSellerDaysData best_seller_days) {
        this.best_seller_days = best_seller_days;
    }

    public HomeStoreInfoData getStore_info() {
        return store_info;
    }

    public void setStore_info(HomeStoreInfoData store_info) {
        this.store_info = store_info;
    }

    public List<Product> getBest_products() {
        return best_products;
    }

    public void setBest_products(List<Product> best_products) {
        this.best_products = best_products;
    }

    public List<HomeData> getUser_rate() {
        return user_rate;
    }

    public void setUser_rate(List<HomeData> user_rate) {
        this.user_rate = user_rate;
    }
}

