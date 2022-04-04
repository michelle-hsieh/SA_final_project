package com.example.sa_f.database;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAPIService {
    //api_key請輸入自己的  restaurant是我自己另外創的資料表
    //抓取
    @GET("Customer?view=Grid_test&api_key=keyQgefWhH0oVJRvQ")
    Call<record> getCustomer();

    @GET("Restaurant?view=Grid%20view&api_key=key1PTOgUgxJQUL59")
    Call<record> getItem();

    @GET("Restaurant?view=Grid%20score&api_key=keyDZpLxtq3OPlaSk")
    Call<record> getInfor();

    @GET("Diary?Grid%20view&api_key=key1PTOgUgxJQUL59")
    Call<record> getdiaryDetail();

    @GET("Diary?view=Grid%20time&api_key=keyDZpLxtq3OPlaSk")
    Call<record> getdiary();

    @GET("Diary?view=Grid%20view&api_key=keyDZpLxtq3OPlaSk")
    Call<record> getPersonalDiary();

    @GET("News?view=Grid%20view&api_key=key1PTOgUgxJQUL59")
    Call<record> getnewsDetail();

    @GET("News?view=Grid%20time&api_key=key1PTOgUgxJQUL59")
    Call<record> getnews();

    @GET("Message?view=Grid%20time&api_key=keyAgfr3VXLty6ORY")
    Call<record> getmessage();



    @GET("Score?view=Grid%20view&api_key=key1PTOgUgxJQUL59")
    Call<record> getscore();
/*
    @POST("Score?view=Grid%20view&api_key=key1PTOgUgxJQUL59") // 用@Body表示要傳送Body資料
    @Headers({
            "Accept: application/json; charset=utf-8",
            "Content-Type: application/json; charset=utf-8"
    })
    Call<record> postscore(@Body fields fields);

    @POST("Message?view=Grid%20view&api_key=key1PTOgUgxJQUL59") // 用@Body表示要傳送Body資料
    @Headers({
            "Accept: application/json; charset=utf-8",
            "Content-Type: application/json; charset=utf-8"
    })
    Call<record> postmes(@Body fields fields);
*/


//    @GET("albums/{id}") // 用{}表示路徑參數，@Path會將參數帶入至該位置
//    Call<Infor> getAlbumsById(@Path("id") int id);

    //新增
    @Headers({
            "Accept: application/json; charset=utf-8",
            "Content-Type: application/json; charset=utf-8"
    })
    @POST("Diary?api_key=key1PTOgUgxJQUL59") // 用@Body表示要傳送Body資料
    Call<diaryInfo> postInfor(@Body diaryInfo di);


 //刪除    recOzDwjVrTf2rj5b->id
    @DELETE("Diary?view=Grid%20view&api_key=keyDZpLxtq3OPlaSk")
    Call<Infor> deleteInfor(@Path("id") String id);

/***
 //修改
    @PATCH("hi/recOzDwjVrTf2rj5b?api_key=keyKsJNFtZhy4rUjk")
    @Headers({
            "Accept: application/json; charset=utf-8",
            "Content-Type: application/json; charset=utf-8"
    })
    Call<Infor> changeInfor(@Body Req fields);
    ***/
}