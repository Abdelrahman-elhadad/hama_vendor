package hama.alsaygh.kw.vendor.model.image;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Image implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("file_name")
    private String file_name;

    @SerializedName("url")
    private String url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
