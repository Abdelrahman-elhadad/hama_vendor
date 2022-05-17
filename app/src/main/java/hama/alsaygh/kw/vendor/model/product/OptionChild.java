package hama.alsaygh.kw.vendor.model.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OptionChild implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("available_quantity")
    private int available_quantity;

    @SerializedName("quantity_for_sale")
    private int quantity_for_sale;


    @SerializedName("additional_cost")
    private double additional_cost;

    @SerializedName("final_cost")
    private double final_cost;


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

    public int getQuantity_for_sale() {
        return quantity_for_sale;
    }

    public void setQuantity_for_sale(int quantity_for_sale) {
        this.quantity_for_sale = quantity_for_sale;
    }

    public double getAdditional_cost() {
        return additional_cost;
    }

    public void setAdditional_cost(double additional_cost) {
        this.additional_cost = additional_cost;
    }

    public double getFinal_cost() {
        return final_cost;
    }

    public void setFinal_cost(double final_cost) {
        this.final_cost = final_cost;
    }

    @Override
    public String toString() {
        return name;
    }
}
