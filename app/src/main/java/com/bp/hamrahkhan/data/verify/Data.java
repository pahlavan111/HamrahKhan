package com.bp.hamrahkhan.data.verify;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("codeValidation")
    private boolean codeValidation;

    @SerializedName("token")
    private String token;

    @SerializedName("refreshToken")
    private String refreshToken;

    @SerializedName("profile")
    Profile ProfileObject;

    @SerializedName("intro")
    private boolean intro;

    public Data(boolean codeValidation, String token, String refreshToken, Profile profileObject, boolean intro) {
        this.codeValidation = codeValidation;
        this.token = token;
        this.refreshToken = refreshToken;
        ProfileObject = profileObject;
        this.intro = intro;
    }

    // Getter Methods

    public boolean getCodeValidation() {
        return codeValidation;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Profile getProfile() {
        return ProfileObject;
    }

    public boolean getIntro() {
        return intro;
    }

    // Setter Methods

    public void setCodeValidation(boolean codeValidation) {
        this.codeValidation = codeValidation;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setProfile(Profile profileObject) {
        this.ProfileObject = profileObject;
    }

    public void setIntro(boolean intro) {
        this.intro = intro;
    }
}
