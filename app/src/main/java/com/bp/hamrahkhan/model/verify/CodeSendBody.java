package com.bp.hamrahkhan.model.verify;

import com.google.gson.annotations.SerializedName;

public class CodeSendBody {

    @SerializedName("mobile")
    private Long mobile;

    @SerializedName("verificationCode")
    private String verificationCode;

    @SerializedName("referrer")
    private int referrer;

    @SerializedName("referrerCode")
    private String referrerCode;

    public CodeSendBody(Long mobile, String verificationCode, int referrer, String referrerCode) {
        this.mobile = mobile;
        this.verificationCode = verificationCode;
        this.referrer = referrer;
        this.referrerCode = referrerCode;
    }

    public long getMobile() {
        return mobile;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public String getReferrerCode() {
        return referrerCode;
    }

    public void setMobile( long mobile ) {
        this.mobile = mobile;
    }

    public void setVerificationCode( String verificationCode ) {
        this.verificationCode = verificationCode;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public int getReferrer() {
        return referrer;
    }

    public void setReferrer(int referrer) {
        this.referrer = referrer;
    }

    public void setReferrerCode(String referrerCode ) {
        this.referrerCode = referrerCode;
    }
}
