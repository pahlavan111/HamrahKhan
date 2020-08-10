package com.bp.hamrahkhan.model.sms;

import com.google.gson.annotations.SerializedName;

public class MobileSendBody {

    @SerializedName("mobile")
    private Long mobile;
    @SerializedName("apiCode")
    private String apiCode;

    public MobileSendBody(Long mobile, String apiCode) {
        this.mobile = mobile;
        this.apiCode = apiCode;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }
}
