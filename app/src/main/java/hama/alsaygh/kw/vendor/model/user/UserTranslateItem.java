package hama.alsaygh.kw.vendor.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserTranslateItem implements Serializable {


    @SerializedName(value = "locale")
    private String locale;

    @SerializedName("company_name")
    private String company_name;

    @SerializedName("company_description")
    private String company_description;


    public UserTranslateItem() {
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCompany_name() {
        if (company_name == null)
            company_name = "";
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_description() {
        if (company_description == null)
            company_description = "";
        return company_description;
    }

    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }
}