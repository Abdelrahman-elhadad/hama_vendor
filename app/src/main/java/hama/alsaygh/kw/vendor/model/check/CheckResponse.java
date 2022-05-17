package hama.alsaygh.kw.vendor.model.check;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckResponse implements Serializable {

    @SerializedName("status")
    private boolean status=false;

    @SerializedName("code")
    private String code="";

    @SerializedName("message")
    private String message="";


    public CheckResponse() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
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
