package hama.alsaygh.kw.vendor.model.marketPrice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainCaliberItem {


    @SerializedName("name")
    private String name;


    private List<CaliberItem> caliberItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CaliberItem> getCaliberItems() {
        return caliberItems;
    }

    public void setCaliberItems(List<CaliberItem> caliberItems) {
        this.caliberItems = caliberItems;
    }
}

