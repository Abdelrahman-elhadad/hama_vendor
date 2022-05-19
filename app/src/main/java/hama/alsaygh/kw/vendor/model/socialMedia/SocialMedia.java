package hama.alsaygh.kw.vendor.model.socialMedia;

import com.google.gson.annotations.SerializedName;

public class SocialMedia {

    @SerializedName("facebook")
    private String facebook;

    @SerializedName("twitter")
    private String twitter;

    @SerializedName("instagram")
    private String instagram;

    @SerializedName("youtube")
    private String youtube;

    @SerializedName("linkedin")
    private String linkedin;

    public String getFacebook() {
        if (facebook == null)
            facebook = "";
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        if (twitter == null)
            twitter = "";
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        if (instagram == null)
            instagram = "";
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYoutube() {
        if (youtube == null)
            youtube = "";
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getLinkedin() {
        if (linkedin == null)
            linkedin = "";
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
