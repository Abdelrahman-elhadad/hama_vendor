package hama.alsaygh.kw.vendor.model.country;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

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

    @Override
    public String toString() {
        return name;

    }
}
