package hama.alsaygh.kw.vendor.model.home;

import com.google.gson.annotations.SerializedName;

public class HomeStoreInfoData {


    @SerializedName(value = "visitors_count")
    private int visitors_count;

    @SerializedName(value = "last_week_orders")
    private double last_week_orders;

    @SerializedName(value = "last_week_orders_avg")
    private String last_week_orders_avg;

    @SerializedName(value = "most_age_group")
    private String most_age_group;

    public int getVisitors_count() {
        return visitors_count;
    }

    public void setVisitors_count(int visitors_count) {
        this.visitors_count = visitors_count;
    }

    public double getLast_week_orders() {
        return last_week_orders;
    }

    public void setLast_week_orders(double last_week_orders) {
        this.last_week_orders = last_week_orders;
    }

    public String getLast_week_orders_avg() {
        if (last_week_orders_avg == null)
            last_week_orders_avg = "";
        return last_week_orders_avg;
    }

    public void setLast_week_orders_avg(String last_week_orders_avg) {
        this.last_week_orders_avg = last_week_orders_avg;
    }

    public String getMost_age_group() {
        if (most_age_group == null)
            most_age_group = "";
        return most_age_group;
    }

    public void setMost_age_group(String most_age_group) {
        this.most_age_group = most_age_group;
    }
}

