package com.bp.hamrahkhan.data.verify;

public class CodeSend {

    private Long mobile;
    private Long verificationCode;
    private int referrer;
    private String referrerCode;

    public CodeSend(Long mobile, Long verificationCode, int referrer, String referrerCode) {
        this.mobile = mobile;
        this.verificationCode = verificationCode;
        this.referrer = referrer;
        this.referrerCode = referrerCode;
    }

    // Getter Methods

    public long getMobile() {
        return mobile;
    }

    public Long getVerificationCode() {
        return verificationCode;
    }


    public String getReferrerCode() {
        return referrerCode;
    }

    // Setter Methods

    public void setMobile( long mobile ) {
        this.mobile = mobile;
    }

    public void setVerificationCode( Long verificationCode ) {
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
