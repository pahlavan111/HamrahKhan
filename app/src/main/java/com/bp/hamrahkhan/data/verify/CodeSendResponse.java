package com.bp.hamrahkhan.data.verify;

public class CodeSendResponse {
    Data DataObject;
    private float Code;
    private String Message;


    // Getter Methods

    public Data getData() {
        return DataObject;
    }

    public float getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }

    // Setter Methods

    public void setData(Data DataObject) {
        this.DataObject = DataObject;
    }

    public void setCode(float Code) {
        this.Code = Code;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
