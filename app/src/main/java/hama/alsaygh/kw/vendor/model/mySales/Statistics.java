package hama.alsaygh.kw.vendor.model.mySales;

import com.google.gson.annotations.SerializedName;

public class Statistics {


    @SerializedName("1")
    private double jan;

    @SerializedName("2")
    private double feb;

    @SerializedName("3")
    private double march;

    @SerializedName("4")
    private double april;

    @SerializedName("5")
    private double may;

    @SerializedName("6")
    private double june;

    @SerializedName("7")
    private double july;

    @SerializedName("8")
    private double august;

    @SerializedName("9")
    private double september;

    @SerializedName("10")
    private double october;

    @SerializedName("11")
    private double november;

    @SerializedName("12")
    private double december;

    public double getJan() {
        if (jan == 0)
            jan = 1500;
        return jan;
    }

    public void setJan(double jan) {
        this.jan = jan;
    }

    public double getFeb() {
        if (feb == 0)
            feb = 1000;
        return feb;
    }

    public void setFeb(double feb) {
        this.feb = feb;
    }

    public double getMarch() {
        if (march == 0)
            march = 500;
        return march;
    }

    public void setMarch(double march) {
        this.march = march;
    }

    public double getApril() {
        if (april == 0)
            april = 700;
        return april;
    }

    public void setApril(double april) {
        this.april = april;
    }

    public double getMay() {
        if (may == 0)
            may = 900;
        return may;
    }

    public void setMay(double may) {
        this.may = may;
    }

    public double getJune() {
        if (june == 0)
            june = 1500;
        return june;
    }

    public void setJune(double june) {
        this.june = june;
    }

    public double getJuly() {
        return july;
    }

    public void setJuly(double july) {
        this.july = july;
    }

    public double getAugust() {
        return august;
    }

    public void setAugust(double august) {
        this.august = august;
    }

    public double getSeptember() {
        return september;
    }

    public void setSeptember(double september) {
        this.september = september;
    }

    public double getOctober() {
        return october;
    }

    public void setOctober(double october) {
        this.october = october;
    }

    public double getNovember() {
        return november;
    }

    public void setNovember(double november) {
        this.november = november;
    }

    public double getDecember() {
        return december;
    }

    public void setDecember(double december) {
        this.december = december;
    }

    public double getTotal() {
        return getJan() + getFeb() + getMarch() + getApril() + getMay() +
                getJune() + getJuly() + getAugust() + getSeptember() + getOctober() +
                getNovember() + getDecember();
    }
}

