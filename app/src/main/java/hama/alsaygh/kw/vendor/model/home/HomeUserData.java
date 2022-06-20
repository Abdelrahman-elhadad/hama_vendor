package hama.alsaygh.kw.vendor.model.home;

import com.google.gson.annotations.SerializedName;

public class HomeUserData {


    @SerializedName(value = "full_name")
    private String full_name;

    @SerializedName(value = "avatar")
    private String avatar;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

