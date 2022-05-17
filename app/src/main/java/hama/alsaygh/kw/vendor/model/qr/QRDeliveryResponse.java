package hama.alsaygh.kw.vendor.model.qr;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QRDeliveryResponse implements Serializable {

    @SerializedName("status")
    private boolean status=false;

    @SerializedName("code")
    private String code="";

    @SerializedName("message")
    private String message="";

    @SerializedName("data")
    private QRDelivery data;


    public QRDeliveryResponse() {
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

    public QRDelivery getData() {
        return data;
    }

    public void setData(QRDelivery data) {
        this.data = data;
    }
}
