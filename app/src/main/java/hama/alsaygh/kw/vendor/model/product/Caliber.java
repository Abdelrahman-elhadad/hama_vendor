package hama.alsaygh.kw.vendor.model.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Caliber implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("value")
    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
