package com.bp.hamrahkhan.data;

public class MobileSendResponse {

    Data Data;
    private int Code;
    private String Message;


    public com.bp.hamrahkhan.data.Data getData() {
        return Data;
    }

    public void setData(com.bp.hamrahkhan.data.Data data) {
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
