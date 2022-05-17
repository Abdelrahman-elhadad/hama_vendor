package hama.alsaygh.kw.vendor.model.appointment;

import com.google.gson.annotations.SerializedName;

public class Appointment {

    @SerializedName("appointments_title")
    private String appointments_title;

    @SerializedName("appointments_desc")
    private String appointments_desc;

    @SerializedName("worktime")
    private String worktime;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    public String getAppointments_title() {
        return appointments_title;
    }

    public void setAppointments_title(String appointments_title) {
        this.appointments_title = appointments_title;
    }

    public String getAppointments_desc() {
        return appointments_desc;
    }

    public void setAppointments_desc(String appointments_desc) {
        this.appointments_desc = appointments_desc;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
