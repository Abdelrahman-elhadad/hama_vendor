package hama.alsaygh.kw.vendor.model.country;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class City {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("added_to_delegate")
    private boolean added_to_delegate;

    private boolean selected=false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isAdded_to_delegate() {
        return added_to_delegate;
    }

    public void setAdded_to_delegate(boolean added_to_delegate) {
        this.added_to_delegate = added_to_delegate;
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

    @NonNull
    @Override
    public String toString() {
        return name;

    }
}
