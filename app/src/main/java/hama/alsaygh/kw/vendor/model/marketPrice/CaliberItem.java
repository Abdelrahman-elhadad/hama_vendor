package hama.alsaygh.kw.vendor.model.marketPrice;

import com.google.gson.annotations.SerializedName;

public class CaliberItem {


    @SerializedName("caliber")
    private String caliber;

    @SerializedName("is_up")
    private String is_up;

    @SerializedName("price")
    private double price;

    public boolean isUp() {
        return !getIs_up().equalsIgnoreCase("0");
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public String getIs_up() {
        return is_up;
    }

    public void setIs_up(String is_up) {
        this.is_up = is_up;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

