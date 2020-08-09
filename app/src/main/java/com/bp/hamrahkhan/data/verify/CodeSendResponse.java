package com.bp.hamrahkhan.data.verify;

import com.google.gson.annotations.SerializedName;

public class CodeSendResponse {

    @SerializedName("Data")
    Data DataObject;
    @SerializedName("Code")
    private int Code;
    @SerializedName("Message")
    private String Message;

    public CodeSendResponse(Data dataObject, int code, String message) {
        DataObject = dataObject;
        Code = code;
        Message = message;
    }

    public Data getData() {
        return DataObject;
    }

    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setData(Data DataObject) {
        this.DataObject = DataObject;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
