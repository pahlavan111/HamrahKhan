package com.bp.hamrahkhan.model.path;

import com.google.gson.annotations.SerializedName;

public class GetPathResponse {

    @SerializedName("Data")
    Data data;
    @SerializedName("Code")
    private int Code;
    @SerializedName("Message")
    private String Message;

    public GetPathResponse(Data data, int code, String message) {
        this.data = data;
        this.Code = code;
        Message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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
        Message = message;
    }
}
