package com.example.sa_f.profile_related;

public class DiaryItems {
    private String diary_pic_url, diary_topic, diary_content;
    private int diary_id;
    private int[] diary_resId;
    private boolean is_edit;


    public DiaryItems(String diary_pic_url, String diary_topic, String diary_content, int diary_id, int[] diary_resId) {
        this.diary_topic = diary_topic;
        this.diary_pic_url = diary_pic_url;
        this.diary_content = diary_content;
        this.diary_id = diary_id;
        this.diary_resId = diary_resId;
        is_edit = false;
    }

    public String getDiaryTopic() {
        return diary_topic;
    }

    public String getDiaryPicUrl() {
        return diary_pic_url;
    }

    public String getDiaryContent() {
        return diary_content;
    }

    public int getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(int diary_id) {
        this.diary_id = diary_id;
    }

    public int getDiary_resId() {
        return diary_resId[0];
    }

    public void setDiary_resId(int[] diary_resId) {
        this.diary_resId = diary_resId;
    }

    public boolean getIsEdit() {
        return is_edit;
    }

    public void setIsEdit(boolean is_edit) {
        this.is_edit = is_edit;
    }

}
