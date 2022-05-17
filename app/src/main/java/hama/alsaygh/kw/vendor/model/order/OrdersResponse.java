package hama.alsaygh.kw.vendor.model.order;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class OrdersResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Order> data;

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

    public List<Order> getData() {
        if(data==null)
            data=new ArrayList<>();
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
