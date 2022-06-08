package hama.alsaygh.kw.vendor.model.general;

import com.google.gson.annotations.SerializedName;


public class GeneralResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;


    public boolean isStatus() {
        return status != null && status.equalsIgnoreCase("true");
    }

    public void setStatus(boolean status) {
        if (status)
            this.status = "true";
        else
            this.status = "false";
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
