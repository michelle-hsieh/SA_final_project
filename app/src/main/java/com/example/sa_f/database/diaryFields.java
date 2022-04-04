package com.example.sa_f.database;

import com.google.gson.annotations.SerializedName;

public class diaryFields {

    @SerializedName("diary_topic")
    private String diary_topic;
    @SerializedName("diary_content")
    private String diary_content;
    @SerializedName("is_share")
    private String is_share;

    public diaryFields() {}

    public String getDiary_topic() {
        return diary_topic;
    }

    public void setDiary_topic(String diary_topic) {
        this.diary_topic = diary_topic;
    }

    public String getDiary_content() {
        return diary_content;
    }

    public void setDiary_content(String diary_content) {
        this.diary_content = diary_content;
    }

    public String getIs_share() {
        return is_share;
    }

    public void setIs_share(String is_share) {
        this.is_share = is_share;
    }

}
