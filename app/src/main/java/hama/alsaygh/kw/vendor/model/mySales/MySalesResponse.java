package hama.alsaygh.kw.vendor.model.mySales;


import com.google.gson.annotations.SerializedName;


public class MySalesResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private MySalesData data;


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

    public MySalesData getData() {
        return data;
    }

    public void setData(MySalesData data) {
        this.data = data;
    }

}
