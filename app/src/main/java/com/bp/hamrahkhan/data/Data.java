package com.bp.hamrahkhan.data;

public class Data {

    private Boolean mobileValidation;
    private Boolean newPassenger;

    public Data(boolean mobileValidation, boolean newPassenger) {
        this.mobileValidation = mobileValidation;
        this.newPassenger = newPassenger;
    }

    public boolean isMobileValidation() {
        return mobileValidation;
    }

    public void setMobileValidation(boolean mobileValidation) {
        this.mobileValidation = mobileValidation;
    }

    public boolean isNewPassenger() {
        return newPassenger;
    }

    public void setNewPassenger(boolean newPassenger) {
        this.newPassenger = newPassenger;
    }
}
