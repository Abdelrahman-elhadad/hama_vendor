package hama.alsaygh.kw.vendor.model.address;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import hama.alsaygh.kw.vendor.model.country.Country;


public class Address implements Serializable {


    @SerializedName("id")
    private int id;

    @SerializedName("country")
    private Country country;

    @SerializedName("city")
    private String city;

    @SerializedName("street")
    private String street;

    @SerializedName("zip_code")
    private String zip_code;

    @SerializedName("building_no")
    private String building_no;

    @SerializedName("is_primary")
    private boolean primary;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getBuilding_no() {
        return building_no;
    }

    public void setBuilding_no(String building_no) {
        this.building_no = building_no;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getBuilding_no()+","+getStreet()+","+getCountry().getName();
    }
}

