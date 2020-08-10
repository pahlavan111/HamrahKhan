package com.bp.hamrahkhan.model.path;

import com.google.gson.annotations.SerializedName;

public class GetPathBody {

    @SerializedName("mobile")
    private Long mobile;
    @SerializedName("value")
    private String value;
    @SerializedName("city")
    private int city;
    @SerializedName("page")
    private int page;
    @SerializedName("sourceStation")
    private int sourceStation;
    @SerializedName("destinationStation")
    private int destinationStation;
    @SerializedName("pathType")
    private String pathType;


    public GetPathBody(Long mobile, String value, int city, int page, int sourceStation, int destinationStation, String pathType) {
        this.mobile = mobile;
        this.value = value;
        this.city = city;
        this.page = page;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.pathType = pathType;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(int sourceStation) {
        this.sourceStation = sourceStation;
    }

    public int getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(int destinationStation) {
        this.destinationStation = destinationStation;
    }

    public String getPathType() {
        return pathType;
    }

    public void setPathType(String pathType) {
        this.pathType = pathType;
    }
}
