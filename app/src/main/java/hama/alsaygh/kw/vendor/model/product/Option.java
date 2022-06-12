package hama.alsaygh.kw.vendor.model.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Option implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("name_ar")
    private String nameAr;

    @SerializedName("available_quantity")
    private int available_quantity;

    @SerializedName("color")
    private String color;

    @SerializedName("total_weight")
    private String total_weight;

    @SerializedName("bind_to_market")
    private boolean bind_to_market;

    @SerializedName("price")
    private double price;

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTotal_weight() {
        if (total_weight == null || total_weight.isEmpty())
            total_weight = "0.0";
        return total_weight;
    }

    public void setTotal_weight(String total_weight) {
        this.total_weight = total_weight;
    }

    public boolean isBind_to_market() {
        return bind_to_market;
    }

    public void setBind_to_market(boolean bind_to_market) {
        this.bind_to_market = bind_to_market;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
