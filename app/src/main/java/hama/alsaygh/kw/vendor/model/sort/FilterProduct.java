package hama.alsaygh.kw.vendor.model.sort;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FilterProduct implements Serializable {


    @SerializedName("id")
    private int id;

    @SerializedName("key")
    private String key;


    @SerializedName("trans")
    private String trans;


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

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }
}
