package hama.alsaygh.kw.vendor.model.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("count")
    private int count;

    @SerializedName("sub_total")
    private double sub_total;

    @SerializedName("shipping")
    private double shipping;

    @SerializedName("total")
    private double total;

    @SerializedName("cart")
    private List<CartItem> cart;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }
}
