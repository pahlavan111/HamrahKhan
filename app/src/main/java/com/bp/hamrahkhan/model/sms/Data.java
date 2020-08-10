package com.bp.hamrahkhan.model.sms;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("mobileValidation")
    private boolean mobileValidation;
    @SerializedName("newUser")
    private boolean newUser;

    public boolean getMobileValidation() {
        return mobileValidation;
    }

    public boolean getNewUser() {
        return newUser;
    }

    public void setMobileValidation(boolean mobileValidation) {
        this.mobileValidation = mobileValidation;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

}
