package com.bp.hamrahkhan.data.sms;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("mobileValidation")
    private boolean mobileValidation;
    @SerializedName("newUser")
    private boolean newUser;

    // Getter Methods

    public boolean getMobileValidation() {
        return mobileValidation;
    }

    public boolean getNewUser() {
        return newUser;
    }

    // Setter Methods

    public void setMobileValidation(boolean mobileValidation) {
        this.mobileValidation = mobileValidation;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

}
