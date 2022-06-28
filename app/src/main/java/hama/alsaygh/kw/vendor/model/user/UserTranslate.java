package hama.alsaygh.kw.vendor.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserTranslate implements Serializable {

    @SerializedName("ar")
    private UserTranslateItem ar;

    @SerializedName("en")
    private UserTranslateItem en;


    public UserTranslate() {
    }

    public UserTranslateItem getAr() {
        if (ar == null)
            ar = new UserTranslateItem();
        return ar;
    }

    public void setAr(UserTranslateItem ar) {
        this.ar = ar;
    }

    public UserTranslateItem getEn() {
        if (en == null)
            en = new UserTranslateItem();
        return en;
    }

    public void setEn(UserTranslateItem en) {
        this.en = en;
    }
}