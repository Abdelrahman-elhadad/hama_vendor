package hama.alsaygh.kw.vendor.model.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Translations implements Serializable {

    @SerializedName("ar")
    private TranslationsItem ar;

    @SerializedName("en")
    private TranslationsItem en;

    public TranslationsItem getAr() {
        return ar;
    }

    public void setAr(TranslationsItem ar) {
        this.ar = ar;
    }

    public TranslationsItem getEn() {
        return en;
    }

    public void setEn(TranslationsItem en) {
        this.en = en;
    }
}
