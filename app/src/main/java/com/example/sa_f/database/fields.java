package com.example.sa_f.database;

import com.google.gson.annotations.SerializedName;

import java.lang.ref.SoftReference;

public class fields {
    /*Customer*/
    @SerializedName("cus_acc")
    private String cus_acc;
    @SerializedName("cus_pw")
    private String cus_pw;

    /*restaurant*/
    @SerializedName("res_name")
    private String res_name;
    @SerializedName("fav_res")
    private String[] fav_res;
    @SerializedName("history_res")
    private String[] history_res;
    @SerializedName("res_score")
    private double res_score;
    @SerializedName("res_click")
    private int res_click;
    @SerializedName("res_id")
    private int res_id;
    @SerializedName("res_addr")
    private String res_addr;
    @SerializedName("res_phone")
    private String res_phone;
    @SerializedName("open_hr")
    private String open_hr;
    @SerializedName("fav_count")
    private int fav_count;

    /*diary*/
    @SerializedName("diary_topic")
    private String diary_topic;
    @SerializedName("diary_create")
    private String diary_create;
    @SerializedName("cus_name")
    private String[] cus_name;
    @SerializedName("diary_content")
    private String diary_content;
    @SerializedName("diary_pic")
    private diaryPic[] diary_pic;
    @SerializedName("is_share")
    private String is_share;
    @SerializedName("diary_report")
    private int diary_report;
    @SerializedName("diary_editor")
    private String[] diary_editor;
    @SerializedName("diary_target")
    private String[] diary_target;
    @SerializedName("diary_id")
    private int diary_id;
    @SerializedName("diary_restaurant")
    private String[] diary_restaurant;
    @SerializedName("diary_resId")
    private int[] diary_resId;

    /*news*/
    @SerializedName("news_topic")
    private String news_topic;
    @SerializedName("news_create")
    private String news_create;
    @SerializedName("news_content")
    private String news_content;
    @SerializedName("editor_res")
    private String[] editor_res;
    @SerializedName("news_id")
    private int news_id;
    @SerializedName("id_res")
    private int[] id_res;
    //@SerializedName("diary_pic")
    //private String[] diary_pic;

    //score
    @SerializedName("score_id")
    private int score_id;
    @SerializedName("score_acc")
    private String[] score_acc;
    @SerializedName("score_resid")
    private int[] score_resid;
    @SerializedName("score_grade")
    private int score_grade;
    @SerializedName("score_target")
    private String [] score_target;
    @SerializedName("score_editor")
    private String[] score_editor;

    //message

    @SerializedName("mes_content")
    private String mes_content;
    @SerializedName("mes_target")
    private String[] mes_target;
    @SerializedName("mes_editor")
    private String[] mes_editor;
    @SerializedName("mes_who")
    private String[] mes_who;
    @SerializedName("mes_create")
    private String mes_create;
    @SerializedName("mes_resid")
    private int[] mes_resid;
    @SerializedName("mes_like")
    private int mes_like;
    @SerializedName("mes_report")
    private int mes_report;


    public fields(){
        this.res_name = "";
        this.fav_res = null;
        this.history_res = null;
        this.res_score = 0;
        this.res_click = 0;
        this.diary_topic = "";
        this.diary_create = "";
        this.cus_name = null;
        this.news_topic = "";
        this.news_create = "";
        this.news_content = "";
        this.editor_res = null;
        this.diary_content = "";
        this.diary_pic = null;
        this.is_share = "";
        this.diary_report = 0;
        this.diary_editor = null;
        this.diary_target = null;
    }

    public fields(String cus_acc,
                  String cus_pw,
                  String res_name,
                  String[] fav_res,
                  String[] history_res,
                  double res_score,
                  int res_click,
                  int fav_count,
                  String diary_topic,
                  String diary_create,
                  int diary_id,
                  String[] diary_restaurant,
                  int[] diary_resId,
                  String[] cus_name,
                  String news_topic,
                  String news_create,
                  String news_content,
                  String[] editor_res,
                  String diary_content,
                  String is_share,
                  int diary_report,
                  String[] diary_editor,
                  String[] diary_target,
                  diaryPic[] diary_pic,
                  int res_id,
                  String res_addr,
                  String res_phone,
                  String open_hr,
                  int news_id,
                  int[] id_res,
                  String mes_content, String mes_create, String[] mes_target, String[] mes_editor, String[] mes_who, int[] mes_resid, int mes_like, int mes_report
                  /*int score_id, String[] score_acc, int[] score_resid, int score_grade*/) {
        this.cus_acc = cus_acc;
        this.cus_pw = cus_pw;
        this.res_name = res_name;
        this.fav_res = fav_res;
        this.history_res = history_res;
        this.res_score = res_score;
        this.res_click = res_click;
        this.fav_count = fav_count;
        this.diary_topic = diary_topic;
        this.diary_create = diary_create;
        this.cus_name = cus_name;
        this.diary_id = diary_id;
        this.diary_restaurant = diary_restaurant;
        this.diary_resId = diary_resId;
        this.news_topic = news_topic;
        this.news_create = news_create;
        this.news_content = news_content;
        this.editor_res = editor_res;
        this.diary_content = diary_content;
        this.diary_pic = diary_pic;
        this.is_share = is_share;
        this.diary_report = diary_report;
        this.diary_editor = diary_editor;
        this.diary_target = diary_target;
        this.res_id = res_id;
        this.res_addr = res_addr;
        this.res_phone = res_phone;
        this.open_hr = open_hr;
        this.news_id = news_id;
        this.id_res = id_res;
        this.mes_resid = mes_resid;
        this.mes_create = mes_create;
        this.mes_who = mes_who;
        this.mes_editor = mes_editor;
        this.mes_target = mes_target;
        this.mes_content = mes_content;
        this.mes_like = mes_like;
        this.mes_report = mes_report;
        /*this.score_id = score_id;
        this.score_acc = score_acc;
        this.score_resid = score_resid;
        this.score_grade = score_grade;*/
    }

    public fields(String [] score_editor, String[] score_target, int score_grade) {//, String [] score_editor, String[] score_target
        //this.score_id = score_id;
        this.score_grade = score_grade;
        this.score_target = score_target;
        this.score_editor = score_editor;
    }

    public fields(String mes_content, String[] mes_editor, String[] mes_target) {
        this.mes_content = mes_content;
        this.mes_target = mes_target;
        this.mes_editor = mes_editor;
    }


    public String getCus_acc() {
        return cus_acc;
    }

    public void setCus_acc(String cus_acc) {
        this.cus_acc = cus_acc;
    }

    public String getCus_pw() {
        return cus_pw;
    }

    public void setCus_pw(String cus_pw) {
        this.cus_pw = cus_pw;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String[] getFav_res() {
        return fav_res;
    }

    public void setFav_res(String[] fav_res) {
        this.fav_res = fav_res;
    }

    public int getFavRes() {return fav_res.length;}

    public int getFavCount(){
        return getFav_res().length;
    }

    public String[] getHistory_res() {
        return history_res;
    }

    public void setHistory_res(String[] history_res) {
        this.history_res = history_res;
    }

    public int getHisCount(){
        return getHistory_res().length;
    }

    public double getRes_score() {
        return res_score;
    }

    public void setRes_score(int res_score) {
        this.res_score = res_score;
    }

    public int getRes_click(){
        return res_click;
    }

    public void setRes_click(){
        this.res_click = res_click;
    }

    public int getRes_id() { return res_id; }

    public void setRes_id(int res_id) {this.res_id = res_id; }

    public String getRes_addr() { return res_addr; }

    public void setRes_addr(String res_addr) { this.res_addr = res_addr; }

    public String getRes_phone() { return res_phone; }

    public void setRes_phone(String res_phone) { this.res_phone = res_phone; }

    public String getOpen_hr() { return open_hr; }

    public void setOpen_hr(String open_hr) { this.open_hr = open_hr; }

    public int getFav_count(){
        return fav_count;
    }

    public void setFav_count(int fav_count){
        this.fav_count = fav_count;
    }


    public String getDiary_topic() {
        return diary_topic;
    }

    public void setDiary_topic(String diary_topic) {
        this.diary_topic = diary_topic;
    }

    public String getDiary_create() {
        return diary_create;
    }

    public void setDiary_create(String diary_create) {
        this.diary_create = diary_create;
    }

    public String getdiaryTime(){
        return getDiary_create().substring(0,10);
    }

    public String getCus_name() {
            return cus_name[0];
        }

    public void setCus_name(String[] cus_name) {
            this.cus_name = cus_name;
    }

    public int[] getDiary_resId(){
        return diary_resId;
    }

    /*public int[] getDiary_resId2(){
        return diary_resId;

    }*/

    public void setDiary_resId(int[] diary_resId){
        this.diary_resId = diary_resId;
    }


    public String getNews_topic() {
        return news_topic;
    }

    public void setNews_topic(String news_topic) {
        this.news_topic = news_topic;
    }

    public String getNews_create() {
        return news_create.substring(0,10);
    }

    public void setNews_create(String news_create) {
        this.news_create = news_create;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getEditor_res() {
        return editor_res[0];
    }

    public void setEditor_res(String[] editor_res) {
        this.editor_res = editor_res;
    }

    public String getDiary_content() {
        return diary_content;
    }

    public void setDiary_content(String diary_content) {
        this.diary_content = diary_content;
    }

    public int getDiary_id(){
        return diary_id;
    }

    public void setDiary_id(int diary_id){
        this.diary_id = diary_id;
    }

    public String getDiary_restaurant(){
        return diary_restaurant[0];
    }

    public void setDiary_restaurant(String[] diary_restaurant) {
        this.diary_restaurant = diary_restaurant;
    }

    public int getNews_id(){
        return news_id;
    }

    public void setNews_id(int news_id){
        this.news_id = news_id;
    }

    public int getId_res(){
        return id_res[0];
    }

    public void setId_res(int[] id_res) {
        this.id_res = id_res;
    }


    public diaryPic[] getDiary_pic() {
        return diary_pic;
    }

    public void setDiary_pic(diaryPic[] diary_pic) {
        this.diary_pic = diary_pic;
    }

    public String getIs_share() {
        return is_share;
    }

    public void setIs_share(String is_share) {
        this.is_share = is_share;
    }

    public int getDiary_report() {
        return diary_report;
    }

    public void setDiary_report(int diary_report) {
        this.diary_report = diary_report;
    }

    public String[] getDiary_editor() {
        return diary_editor;
    }

    public void setDiary_editor(String[] diary_editor) {
        this.diary_editor  = diary_editor;
    }

    public String[] getDiary_target() {
        return diary_target;
    }

    public void setDiary_target(String[] diary_target) {
        this.diary_target = diary_target;
    }

    public int getScore_id() { return score_id; }

    public void setScore_id(int score_id) {this.score_id = score_id; }

    public String getScore_acc() { return score_acc[0]; }

    public void setScore_acc(String[] score_acc) {this.score_acc = score_acc; }

    public int getScore_resid() { return score_resid[0]; }

    public void setScore_resid(int[] score_resid) {this.score_resid = score_resid; }

    public int getScore_grade() { return score_grade; }

    public void setScore_grade(int score_grade) {this.score_grade = score_grade; }

    public String getScore_target() { return score_target[0]; }

    public void setScore_target(String[] score_target) {this.score_target = score_target; }

    public String getScore_editor() { return score_editor[0]; }

    public void setScore_editor(String[] score_editor) {this.score_editor = score_editor; }

    public String getMes_content() { return mes_content; }

    public void setMes_content(String mes_content) { this.mes_content = mes_content; }

    public String getMes_target() { return mes_target[0]; }

    public void setMes_target(String[] mes_target) { this.mes_target = mes_target; }

    public String getMes_editor() { return mes_editor[0]; }

    public void setMes_editor(String[] mes_editor) { this.mes_editor = mes_editor; }

    public String getMes_who() { return mes_who[0]; }

    public void setMes_who(String[] mes_who) { this.mes_who = mes_who; }

    public String getMes_create() { return mes_create; }

    public void setMes_create(String mes_create) { this.mes_create = mes_create;}

    public int getMes_resid(){return mes_resid[0]; }

    public void setMes_resid(int[] resid) { this.mes_resid = mes_resid;}

    public int getMes_like() {return mes_like; }

    public void setMes_like(int mes_like) { this.mes_like = mes_like; }

    public int getMes_report() {return mes_report; }

    public void setMes_report(int mes_report) { this.mes_report = mes_report; }
}