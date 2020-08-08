package com.bp.hamrahkhan.data;

public class MobileSendResponse {

    private Data data;
    private int Code;
    private String Message;

    public MobileSendResponse(Data data, int code, String message) {
        this.data = data;
        this.Code = code;
        this.Message = message;
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
        this.Message = message;
    }
}
