package com.example.sa_f.database;

import com.google.gson.annotations.SerializedName;

public class Infor {

    @SerializedName("id")
    private String id;
    @SerializedName("fields")
    private fields fields;
    @SerializedName("createdTime")
    private String createdTime;

    public Infor() {
    }

    public Infor(String id, com.example.sa_f.database.fields fields, String createdTime) {
        this.id = id;
        this.fields = fields;
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public com.example.sa_f.database.fields getFields() {
        return fields;
    }

    public void setFields(com.example.sa_f.database.fields fields) {
        this.fields = fields;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}