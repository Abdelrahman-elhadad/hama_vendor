package hama.alsaygh.kw.vendor.model.store;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import hama.alsaygh.kw.vendor.model.cart.CartItem;


public class Store implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String store_name;

    @SerializedName("date")
    private String date;

    @SerializedName("items")
    private List<CartItem> products;

    @SerializedName("time")
    private String time;

    @SerializedName("items_count")
    private int items_count;

    @SerializedName("phone_number")
    private String phone_number;

    @SerializedName("address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getItems_count() {
        return items_count;
    }

    public void setItems_count(int items_count) {
        this.items_count = items_count;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
