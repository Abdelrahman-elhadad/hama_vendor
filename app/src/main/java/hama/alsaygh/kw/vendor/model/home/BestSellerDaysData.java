package hama.alsaygh.kw.vendor.model.home;

import com.google.gson.annotations.SerializedName;

public class BestSellerDaysData {


    @SerializedName(value = "Monday", alternate = "الاثنين")
    private double monday;

    @SerializedName(value = "Tuesday", alternate = "الثلاثاء")
    private double tuesday;

    @SerializedName(value = "Wednesday", alternate = "الأربعاء")
    private double wednesday;

    @SerializedName(value = "Thursday", alternate = "الخميس")
    private double thursday;

    @SerializedName(value = "Friday", alternate = "الجمعة")
    private double friday;

    @SerializedName(value = "Saturday", alternate = "السبت")
    private double saturday;

    @SerializedName(value = "Sunday", alternate = "الأحد")
    private double sunday;

    public double getMonday() {
        return monday;
    }

    public void setMonday(double monday) {
        this.monday = monday;
    }

    public double getTuesday() {
        return tuesday;
    }

    public void setTuesday(double tuesday) {
        this.tuesday = tuesday;
    }

    public double getWednesday() {
        return wednesday;
    }

    public void setWednesday(double wednesday) {
        this.wednesday = wednesday;
    }

    public double getThursday() {
        return thursday;
    }

    public void setThursday(double thursday) {
        this.thursday = thursday;
    }

    public double getFriday() {
        return friday;
    }

    public void setFriday(double friday) {
        this.friday = friday;
    }

    public double getSaturday() {
        return saturday;
    }

    public void setSaturday(double saturday) {
        this.saturday = saturday;
    }

    public double getSunday() {
        return sunday;
    }

    public void setSunday(double sunday) {
        this.sunday = sunday;
    }
}

