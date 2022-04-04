package com.example.sa_f.database;

import com.google.gson.annotations.SerializedName;

public class diaryInfo {
    @SerializedName("fields")
    private diaryFields field;

    public diaryInfo() {}

    public diaryFields getField() {
        return field;
    }

    public void setField(diaryFields field) {
        this.field = field;
    }
}
