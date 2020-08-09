package com.bp.hamrahkhan.data.verify;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("name")
    private String name = null;

    @SerializedName("gender")
    private String gender = null;

    @SerializedName("defaultCity")
    private int defaultCity;

    @SerializedName("referringCode")
    private String referringCode;

    @SerializedName("referrer")
    private String referrer = null;

    @SerializedName("defaultWallet")
    private String defaultWallet = null;

    @SerializedName("points")
    private int points;

    public Profile(String name, String gender, int defaultCity, String referringCode, String referrer, String defaultWallet, int points) {
        this.name = name;
        this.gender = gender;
        this.defaultCity = defaultCity;
        this.referringCode = referringCode;
        this.referrer = referrer;
        this.defaultWallet = defaultWallet;
        this.points = points;
    }

    // Getter Methods

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getDefaultCity() {
        return defaultCity;
    }

    public String getReferringCode() {
        return referringCode;
    }

    public String getReferrer() {
        return referrer;
    }

    public String getDefaultWallet() {
        return defaultWallet;
    }

    public int getPoints() {
        return points;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDefaultCity(int defaultCity) {
        this.defaultCity = defaultCity;
    }

    public void setReferringCode(String referringCode) {
        this.referringCode = referringCode;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public void setDefaultWallet(String defaultWallet) {
        this.defaultWallet = defaultWallet;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
