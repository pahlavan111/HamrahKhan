package com.bp.hamrahkhan.model.path;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    @SerializedName("perPage")
    private int perPage;
    @SerializedName("pages")
    private int pages;
    @SerializedName("paths")
    ArrayList<Path> paths;

    public Data(int perPage, int pages, ArrayList<Path> paths) {
        this.perPage = perPage;
        this.pages = pages;
        this.paths = paths;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }
}
