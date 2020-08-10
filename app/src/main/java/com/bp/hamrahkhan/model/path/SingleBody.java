package com.bp.hamrahkhan.model.path;

import com.google.gson.annotations.SerializedName;

public class SingleBody {
    @SerializedName("mobile")
    private Long mobile;


    public SingleBody(Long mobile) {
        this.mobile = mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getMobile() {
        return mobile;
    }
}
