package hama.alsaygh.kw.vendor.model.category;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MainCategory {

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

    @NonNull
    @Override
    public String toString() {
        return name == null || name.isEmpty() ? "" : name;
    }
}
