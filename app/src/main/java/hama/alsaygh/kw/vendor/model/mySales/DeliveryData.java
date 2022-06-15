package hama.alsaygh.kw.vendor.model.mySales;

import com.google.gson.annotations.SerializedName;

public class DeliveryData {


    @SerializedName("hama")
    private double hama;

    @SerializedName("hand_by_hand")
    private double hand_by_hand;

    public double getHama() {

        return hama;
    }

    public void setHama(double hama) {
        this.hama = hama;
    }

    public double getHand_by_hand() {

        return hand_by_hand;
    }

    public void setHand_by_hand(double hand_by_hand) {
        this.hand_by_hand = hand_by_hand;
    }
}

