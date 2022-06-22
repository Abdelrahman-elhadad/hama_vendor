package hama.alsaygh.kw.vendor.model.searchLog;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SearchLog implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("key")
    private String key;

    @SerializedName("type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
