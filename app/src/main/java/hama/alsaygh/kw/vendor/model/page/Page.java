package hama.alsaygh.kw.vendor.model.page;

import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("last_update")
    private String last_update;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
