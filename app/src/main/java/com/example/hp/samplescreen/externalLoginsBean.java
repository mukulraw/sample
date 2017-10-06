package com.example.hp.samplescreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukul on 07/10/17.
 */

public class externalLoginsBean {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("State")
    @Expose
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
