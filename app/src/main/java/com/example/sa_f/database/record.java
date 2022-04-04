package com.example.sa_f.database;

import com.example.sa_f.profile_related.DiaryItems;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class record {
    @SerializedName("records")
    private ArrayList<Infor> recordList;

    public record(ArrayList<Infor> recordList) {
        this.recordList = recordList;
    }

    public ArrayList<Infor> getRecordList() {
        return recordList;
    }

    public void setRecordList(ArrayList<Infor> recordList) {
        this.recordList = recordList;
    }

    public void deleteRecordList(ArrayList<Infor> recordList, String id) {
        recordList.remove(id);
    }
}
