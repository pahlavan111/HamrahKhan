package com.bp.hamrahkhan.model.path;

import com.google.gson.annotations.SerializedName;

public class GetPathResponse {

    @SerializedName("Data")
    Data data;
    @SerializedName("Code")
    private int code ;
    @SerializedName("Message")
    private String Message;

    public GetPathResponse(Data data, int code, String message) {
        this.data = data;
        this.code = code;
        Message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
