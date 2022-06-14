package hama.alsaygh.kw.vendor.model.mySales;

import com.google.gson.annotations.SerializedName;

public class GenderData {


    @SerializedName("males")
    private double males;

    @SerializedName("2")
    private double female;

    @SerializedName("others")
    private double others;

    public double getMales() {
        return males;
    }

    public void setMales(double males) {
        this.males = males;
    }

    public double getFemale() {
        return female;
    }

    public void setFemale(double female) {
        this.female = female;
    }

    public double getOthers() {
        return others;
    }

    public void setOthers(double others) {
        this.others = others;
    }
}

