package hama.alsaygh.kw.vendor.model.mySales;

import com.google.gson.annotations.SerializedName;

public class MySalesData {


    @SerializedName("statistics")
    private Statistics statistics;

    @SerializedName("gender_data")
    private GenderData gender_data;

    @SerializedName("delivery")
    private DeliveryData delivery;

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public GenderData getGender_data() {
        return gender_data;
    }

    public void setGender_data(GenderData gender_data) {
        this.gender_data = gender_data;
    }

    public DeliveryData getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryData delivery) {
        this.delivery = delivery;
    }
}

