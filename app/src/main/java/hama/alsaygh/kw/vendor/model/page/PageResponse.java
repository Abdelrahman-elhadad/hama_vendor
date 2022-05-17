package hama.alsaygh.kw.vendor.model.page;

import com.google.gson.annotations.SerializedName;


public class PageResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Page data;

    public Page getData() {
        return data;
    }

    public void setData(Page data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        if (code == null)
            code = "";
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
