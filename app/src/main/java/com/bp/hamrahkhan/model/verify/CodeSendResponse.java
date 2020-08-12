package com.bp.hamrahkhan.model.verify;


import com.google.gson.annotations.SerializedName;

public class CodeSendResponse {


    @SerializedName("Data")
    Data data;
    @SerializedName("Code")
    private int Code;
    @SerializedName("Message")
    private String Message;

    public CodeSendResponse(Data data, int code, String message) {
        this.data = data;
        Code = code;
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
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
