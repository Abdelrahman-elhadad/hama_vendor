package hama.alsaygh.kw.vendor.model.appointment;

import com.google.gson.annotations.SerializedName;


public class AppointmentResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Appointment data;

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

    public Appointment getData() {
        return data;
    }

    public void setData(Appointment data) {
        this.data = data;
    }
}
