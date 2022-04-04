package com.example.sa_f.database;

import com.google.gson.annotations.SerializedName;

public class diaryPic {
    @SerializedName("url")
    private String url;

    public diaryPic(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
