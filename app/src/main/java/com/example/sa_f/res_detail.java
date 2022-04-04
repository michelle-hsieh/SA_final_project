package com.example.sa_f;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sa_f.database.Infor;
import com.example.sa_f.database.MyAPIService;
import com.example.sa_f.database.RetrofitManager;
import com.example.sa_f.database.fields;
import com.example.sa_f.database.record;
import com.example.sa_f.profile_related.DiaryItemAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class res_detail extends AppCompatActivity {

    MyAPIService myAPIService;

    private Button scorebt;//評分按鈕
    private TextView add;//餐廳地址
    private TextView tel;//餐廳電話
    private TextView time;//營業時間
    private TextView name;//餐廳名
    private TextView looktimes;//點擊率
    private TextView resscore;//餐廳得分
    private TextView send;//留言發送
    private TextInputLayout messageInput;//留言layout
    private TextInputEditText mesedit;//留言框
    private TextView mesname;
    private TextView mestime;
    private TextView mescontent;

    private String[] accid = new String[1];
    private String[] resid = new String[1];


    ArrayList<Infor> array ;

    private int id;//接餐廳id
    private int result = 0;//回傳使用者先前對餐廳評分
    private int scoreid;//取得score最大的流水號
    private int a;//回傳使用者先前對餐廳評分 最大score最大的流水號


    Dialog myDialog;//評分用到popup window

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_detail);

        ImageButton news_return = findViewById(R.id.news_return);
        news_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView news = (TextView) findViewById(R.id.news);

        news.setOnClickListener(new View.OnClickListener() { //點擊查看消息
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                startActivity(intent);
            }
        });


        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//EditText鍵盤不要自動彈出

        scorebt = (Button) findViewById(R.id.scorebutton);
        name = (TextView) findViewById(R.id.res_name);
        add = (TextView) findViewById(R.id.resadd);
        tel = (TextView) findViewById(R.id.restel);
        time = (TextView) findViewById(R.id.restime);
        resscore = (TextView) findViewById(R.id.resscore);
        looktimes = (TextView) findViewById(R.id.looktimes);
        messageInput = (TextInputLayout) findViewById(R.id.mesinput);
        messageInput.setErrorEnabled(true);
        mesedit = (TextInputEditText) findViewById(R.id.mesedit);
        send = (TextView) findViewById(R.id.send);
        mesname = (TextView) findViewById(R.id.mesname);
        mestime = (TextView) findViewById(R.id.mestime);
        mescontent = (TextView) findViewById(R.id.mescon);

        if(GlobalVariables.acc!="") {//判斷有無登入
            scorebt.setOnClickListener(new View.OnClickListener() {//評分
                @Override
                public void onClick(View v) {
                    ShowPopup();
                }
            });

            send.setOnClickListener(new View.OnClickListener() {//留言
                @Override
                public void onClick(View v) {
                    if (mesedit.getText().length() > 50) {
                        messageInput.setError("字數超過");
                    } else if (mesedit.length() == 0) {
                        messageInput.setError("留言為空");
                    } else if(mesedit.getText().length()>0 && mesedit.getText().length()<51){

                        //新增留言程式碼還沒寫
                    }
                }
            });
        }
        else{
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messageInput.setError("請登入");
                }
            });
        }




        try {
            getinfor();//這是拿取餐廳資料
            //getMessinfor();//這是拿取留言資料
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
        }//這是拿取資料
/*
        try {
            //postinfor();//新增資料
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
        }

        try {
            // deleteinfor();刪除資料
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
        }

        try {
            //changeinfor();//修改資料
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
        }
*/

/*        TextView newstext = (TextView)findViewById(R.id.news);//消息跳頁
        newstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(res_detail.this, res_news.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
*/

        myDialog = new Dialog(this);//評分用到popup window

        //連到美食日記
        TextView diary = findViewById(R.id.linkdiary);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(res_detail.this, MyDiary.class);
                intent.putExtra("res_id", id);
                startActivity(intent);
            }
        });

    }

    //讀取餐廳資訊
    public void getinfor() {
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<record> call = myAPIService.getItem();
        //成功透過onresponse回傳 失敗用onfailure回傳
        call.enqueue(new Callback<record>() {
            @Override
            public void onResponse(Call<record> call, Response<record> response) {
                for(int i = 0; i < response.body().getRecordList().size(); i++){
                    if(id == response.body().getRecordList().get(i).getFields().getRes_id()){
                        add.append(response.body().getRecordList().get(i).getFields().getRes_addr());
                        tel.append(response.body().getRecordList().get(i).getFields().getRes_phone());
                        time.append(response.body().getRecordList().get(i).getFields().getOpen_hr());
                        name.append(response.body().getRecordList().get(i).getFields().getRes_name());
                        resscore.append(""+response.body().getRecordList().get(i).getFields().getRes_score());
                        looktimes.append(""+response.body().getRecordList().get(i).getFields().getRes_click());
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<record> call, Throwable t) {
                add.setText(t.getMessage());
                tel.setText(t.getMessage());
                time.setText(t.getMessage());
                name.setText(t.getMessage());
            }
        });

    }
    //讀取使用者對餐廳評分
    public int getScore() {
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<record> call = myAPIService.getscore();
        call.enqueue(new Callback<record>() {
            @Override
            public void onResponse(Call<record> call, Response<record> response) {

                for(int i = 0; i < response.body().getRecordList().size(); i++){
                    scoreid = response.body().getRecordList().get(response.body().getRecordList().size()-1).getFields().getScore_id();
                    if(id == response.body().getRecordList().get(i).getFields().getScore_resid() && GlobalVariables.acc.equals(response.body().getRecordList().get(i).getFields().getScore_acc())){
                        result = response.body().getRecordList().get(i).getFields().getScore_grade();
                        break;
                    }
                }
            }
            @Override
            public void onFailure(Call<record> call, Throwable t) {
            }
        });

        return result;
    }
/*
    //新增留言資訊
    public void postmes() {

        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<record> mess = myAPIService.postmes(new fields(id, GlobalVariables.acc));

        //留言  成功透過onresponse回傳 失敗用onfailure回傳
       mess.enqueue(new Callback<record>() {
            @Override
            public void onResponse(Call<record> call, Response<record> response) {

            }

            @Override
            public void onFailure(Call<record> call, Throwable t) {

            }
        });
    }
*/
/*
 //新增
 public void postinfor() {
 myAPIService = RetrofitManager.getInstance().getAPI();
 Call<record> call = myAPIService.postscore(new fields());
 call.enqueue(new Callback<record>() {
@Override
public void onResponse(Call<record> call, Response<record> response) {


}

@Override
public void onFailure(Call<record> call, Throwable t) {

}
});

 }
*/
/***
 //刪除
 public void deleteinfor() {
 myAPIService = RetrofitManager.getInstance().getAPI();
 Call<ResInfor> call = myAPIService.deleteInfor();
 call.enqueue(new Callback<ResInfor>() {
@Override
public void onResponse(Call<ResInfor> call, Response<ResInfor> response) {
mtext_view_result.setText("success");
}

@Override
public void onFailure(Call<ResInfor> call, Throwable t) {
mtext_view_result.setText(t.getMessage());
}
});
 }
 ***/
    /***
     //修改
     public void changeinfor() {
     myAPIService = RetrofitManager.getInstance().getAPI();
     Call<ResInfor> call = myAPIService.changeInfor(new Req(new fields("11111", "111112")));
     call.enqueue(new Callback<ResInfor>() {
    @Override
    public void onResponse(Call<ResInfor> call, Response<ResInfor> response) {
    mtext_view_result.setText("success");
    }

    @Override
    public void onFailure(Call<ResInfor> call, Throwable t) {
    mtext_view_result.setText(t.getMessage());
    }
    });
     }
     ***/


    public void ShowPopup() {//評分用到popup window
        a = getScore();//取得分數
        final RatingBar star;
        Button close;
        final Button btnsend;
        myDialog.setContentView(R.layout.popup_window);
        close =(Button) myDialog.findViewById(R.id.cancel);
        btnsend = (Button) myDialog.findViewById(R.id.send);
        star = (RatingBar) myDialog.findViewById(R.id.rate);
        star.setNumStars(5);//設定最大星數
        star.setStepSize((float) 1);//設定步進值
        star.setRating((float) a);//顯示分數
        //設監聽器
        star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating == 0){
                    //Toast.makeText(res_detail.this,"請評分",Toast.LENGTH_LONG).show();
                }
                else if(a == rating){
                    btnsend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(res_detail.this,"變更評分才能送出",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else if(rating>0 ){
                    btnsend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //新增評分程式碼還沒寫

                            //Toast.makeText(res_detail.this,"評分已送出",Toast.LENGTH_LONG).show();
                            myDialog.dismiss();
                        }
                    });
                }

                //Toast.makeText(res_detail.this, "rating:" + String.valueOf(rating), Toast.LENGTH_LONG).show();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}
