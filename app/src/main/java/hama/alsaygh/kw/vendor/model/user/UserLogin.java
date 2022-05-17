package hama.alsaygh.kw.vendor.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserLogin implements Serializable {

    @SerializedName("token")
    private String token;

    @SerializedName(value = "delegate" ,alternate = "user")
    private User delegate;


    public UserLogin() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getDelegate() {
        return delegate;
    }

    public void setDelegate(User delegate) {
        this.delegate = delegate;
    }
}
