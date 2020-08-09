package com.bp.hamrahkhan.data;

public class CodeSendResponse {
    Data mData;
    private int Code;
    private String Message;


    public Data getmData() {
        return mData;
    }

    public void setmData(Data mData) {
        this.mData = mData;
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
