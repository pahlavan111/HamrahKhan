package com.bp.hamrahkhan.model.path;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Path {

    @SerializedName("code")
    private String code;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("inventoryCount")
    private int inventoryCount;
    @SerializedName("stations")
    ArrayList<Station> stations;

    public Path(String code, String title, String type, int inventoryCount, ArrayList<Station> stations) {
        this.code = code;
        this.title = title;
        this.type = type;
        this.inventoryCount = inventoryCount;
        this.stations = stations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }
}
