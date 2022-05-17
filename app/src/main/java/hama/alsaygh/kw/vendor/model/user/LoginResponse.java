package hama.alsaygh.kw.vendor.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("status")
    private boolean status=false;

    @SerializedName("code")
    private String code="";

    @SerializedName("message")
    private String message="";

    @SerializedName("data")
    private UserLogin data;


    public LoginResponse() {
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

    public UserLogin getData() {
        return data;
    }

    public void setData(UserLogin data) {
        this.data = data;
    }
}
