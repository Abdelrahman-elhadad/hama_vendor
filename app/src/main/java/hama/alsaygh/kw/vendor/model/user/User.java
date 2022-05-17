package hama.alsaygh.kw.vendor.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import hama.alsaygh.kw.vendor.model.country.Country;

public class User implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private String image;

    @SerializedName("logo")
    private String logo;

    @SerializedName("cover_image")
    private String cover_image;

    @SerializedName("license")
    private String license;

    @SerializedName("license_deadline")
    private String license_deadline;

    @SerializedName(value = "name", alternate = "full_name")
    private String name;

    @SerializedName("serial_number")
    private String serial_number;

    @SerializedName("birth_date")
    private String birth_date;

    @SerializedName("address")
    private String address;

    @SerializedName("rate")
    private float rate;

    @SerializedName(value = "mobile", alternate = "phone")
    private String mobile;

    @SerializedName("email")
    private String email;

    @SerializedName("landline")
    private String landline;

    @SerializedName("gender")
    private String gender;

    @SerializedName("id_no")
    private String id_no;

    @SerializedName("end_at")
    private String end_at;

    @SerializedName("bank_account")
    private String bank_account;

    @SerializedName("iban")
    private String iban;

    @SerializedName("bank")
    private String bank;

    @SerializedName("country")
    private Country country;

    @SerializedName("memorandum_of_association")
    private String memorandum_of_association;

    @SerializedName("commercial_record")
    private String commercial_record;

    @SerializedName("commercial_license")
    private String commercial_license;

    @SerializedName("national_id")
    private String national_id;

    @SerializedName("signature_approval")
    private String signature_approval;

    @SerializedName("subscupation_end_date")
    private String subscupation_end_date;

    @SerializedName("visitors_count")
    private int visitors_count;

    @SerializedName("is_dark_mode")
    private boolean is_dark_mode;


    @SerializedName("language")
    private String language;


    @SerializedName("general_notifications")
    private boolean general_notifications;

    @SerializedName("order_notification")
    private boolean order_notification;

    @SerializedName("event_notification")
    private boolean event_notification;

    @SerializedName("adv_notification")
    private boolean adv_notification;

    @SerializedName("has_rate")
    private boolean has_rate;

    private String password, newPassword, confirmNewPassword;

    public User() {
    }

    public String getPassword() {
        if (password == null)
            password = "";
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        if (newPassword == null)
            newPassword = "";
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        if (confirmNewPassword == null)
            confirmNewPassword = "";
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        if (address == null)
            address = "";
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getName() {
        if (name == null)
            name = "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getBirth_date() {
        if (birth_date == null)
            birth_date = "";
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicense_deadline() {
        return license_deadline;
    }

    public void setLicense_deadline(String license_deadline) {
        this.license_deadline = license_deadline;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getMemorandum_of_association() {
        return memorandum_of_association;
    }

    public void setMemorandum_of_association(String memorandum_of_association) {
        this.memorandum_of_association = memorandum_of_association;
    }

    public String getCommercial_record() {
        return commercial_record;
    }

    public void setCommercial_record(String commercial_record) {
        this.commercial_record = commercial_record;
    }

    public String getCommercial_license() {
        return commercial_license;
    }

    public void setCommercial_license(String commercial_license) {
        this.commercial_license = commercial_license;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getSignature_approval() {
        return signature_approval;
    }

    public void setSignature_approval(String signature_approval) {
        this.signature_approval = signature_approval;
    }

    public String getSubscupation_end_date() {
        return subscupation_end_date;
    }

    public void setSubscupation_end_date(String subscupation_end_date) {
        this.subscupation_end_date = subscupation_end_date;
    }

    public int getVisitors_count() {
        return visitors_count;
    }

    public void setVisitors_count(int visitors_count) {
        this.visitors_count = visitors_count;
    }

    public boolean isIs_dark_mode() {
        return is_dark_mode;
    }

    public void setIs_dark_mode(boolean is_dark_mode) {
        this.is_dark_mode = is_dark_mode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isGeneral_notifications() {
        return general_notifications;
    }

    public void setGeneral_notifications(boolean general_notifications) {
        this.general_notifications = general_notifications;
    }

    public boolean isOrder_notification() {
        return order_notification;
    }

    public void setOrder_notification(boolean order_notification) {
        this.order_notification = order_notification;
    }

    public boolean isEvent_notification() {
        return event_notification;
    }

    public void setEvent_notification(boolean event_notification) {
        this.event_notification = event_notification;
    }

    public boolean isAdv_notification() {
        return adv_notification;
    }

    public void setAdv_notification(boolean adv_notification) {
        this.adv_notification = adv_notification;
    }

    public boolean isHas_rate() {
        return has_rate;
    }

    public void setHas_rate(boolean has_rate) {
        this.has_rate = has_rate;
    }


    public boolean isValid() {
        boolean isValid = true;
        if (!getPassword().isEmpty()) {
            if (getNewPassword().isEmpty())
                isValid = false;
            else {
                if (getConfirmNewPassword().isEmpty())
                    isValid = false;
                else if (!getConfirmNewPassword().equals(getNewPassword()))
                    isValid = false;
            }
        }

        return !getName().isEmpty() && !getBirth_date().isEmpty() && !getAddress().isEmpty() && isValid;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", logo='" + logo + '\'' +
                ", cover_image='" + cover_image + '\'' +
                ", license='" + license + '\'' +
                ", license_deadline='" + license_deadline + '\'' +
                ", name='" + name + '\'' +
                ", serial_number='" + serial_number + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", address='" + address + '\'' +
                ", rate=" + rate +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", landline='" + landline + '\'' +
                ", gender='" + gender + '\'' +
                ", id_no='" + id_no + '\'' +
                ", end_at='" + end_at + '\'' +
                ", bank_account='" + bank_account + '\'' +
                ", iban='" + iban + '\'' +
                ", bank='" + bank + '\'' +
                ", country=" + country +
                ", memorandum_of_association='" + memorandum_of_association + '\'' +
                ", commercial_record='" + commercial_record + '\'' +
                ", commercial_license='" + commercial_license + '\'' +
                ", national_id='" + national_id + '\'' +
                ", signature_approval='" + signature_approval + '\'' +
                ", subscupation_end_date='" + subscupation_end_date + '\'' +
                ", visitors_count=" + visitors_count +
                ", is_dark_mode=" + is_dark_mode +
                ", language='" + language + '\'' +
                ", general_notifications=" + general_notifications +
                ", order_notification=" + order_notification +
                ", event_notification=" + event_notification +
                ", adv_notification=" + adv_notification +
                ", has_rate=" + has_rate +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmNewPassword='" + confirmNewPassword + '\'' +
                '}';
    }
}
