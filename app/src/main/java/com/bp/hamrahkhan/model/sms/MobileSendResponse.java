package com.bp.hamrahkhan.model.sms;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

public class MobileSendResponse {

    @Nullable
    @SerializedName("Data")
    Data Data;
    @SerializedName("Code")
    private int Code;
    @SerializedName("Message")
    private String Message;

    public MobileSendResponse(com.bp.hamrahkhan.model.sms.Data data, int code, String message) {
        Data = data;
        Code = code;
        Message = message;
    }

    public com.bp.hamrahkhan.model.sms.Data getData() {
        return Data;
    }

    public void setData(com.bp.hamrahkhan.model.sms.Data data) {
        Data = data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }
}
