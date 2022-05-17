package hama.alsaygh.kw.vendor.model.qr;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QRDelivery implements Serializable {

    @SerializedName("path")
    private String path;



    public QRDelivery() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
