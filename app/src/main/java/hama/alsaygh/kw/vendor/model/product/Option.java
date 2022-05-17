package hama.alsaygh.kw.vendor.model.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Option implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("childs")
    private List<OptionChild> childs;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OptionChild> getChilds() {
        return childs;
    }

    public void setChilds(List<OptionChild> childs) {
        this.childs = childs;
    }
}
