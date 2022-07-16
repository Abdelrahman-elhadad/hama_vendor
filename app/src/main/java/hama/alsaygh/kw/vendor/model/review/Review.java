package hama.alsaygh.kw.vendor.model.review;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import hama.alsaygh.kw.vendor.model.user.User;

public class Review implements Serializable {
    @SerializedName("rate")
    private float rate;

    @SerializedName("review")
    private String review;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("user")
    private User user;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getReview() {
        if (review == null)
            review = "";
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
