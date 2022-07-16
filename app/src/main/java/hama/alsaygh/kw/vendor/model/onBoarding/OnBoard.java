package hama.alsaygh.kw.vendor.model.onBoarding;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class OnBoard implements Serializable {


    @SerializedName("id")
    private int id;


    @SerializedName("title")
    private String title;


    @SerializedName("short_description")
    private String short_description;


    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        if (title == null || title.isEmpty() || title.trim().equalsIgnoreCase("-"))
            return "";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_description() {
        if (short_description == null || short_description.isEmpty() || short_description.trim().equalsIgnoreCase("-"))
            return "";
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
