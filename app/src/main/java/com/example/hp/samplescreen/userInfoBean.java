package com.example.hp.samplescreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukul on 07/10/17.
 */

public class userInfoBean {

    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("HasRegistered")
    @Expose
    private Boolean hasRegistered;
    @SerializedName("LoginProvider")
    @Expose
    private String loginProvider;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasRegistered() {
        return hasRegistered;
    }

    public void setHasRegistered(Boolean hasRegistered) {
        this.hasRegistered = hasRegistered;
    }

    public String getLoginProvider() {
        return loginProvider;
    }

    public void setLoginProvider(String loginProvider) {
        this.loginProvider = loginProvider;
    }

}
