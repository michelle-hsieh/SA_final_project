package com.example.sa_f;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sa_f.database.Infor;
import com.example.sa_f.database.MyAPIService;
import com.example.sa_f.database.RetrofitManager;
import com.example.sa_f.database.record;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class news_res extends AppCompatActivity {

    MyAPIService myAPIService;
    int newsId;
    int resId;
    private TextView newsTopic;
    private TextView newsDate;
    private TextView newsContent;
    private Button resName;
    ArrayList<Infor> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_res);

        newsTopic = (TextView)findViewById(R.id.news_topic);
        resName = (Button) findViewById(R.id.news_btu);
        newsDate = (TextView)findViewById(R.id.news_date);
        newsContent = (TextView)findViewById(R.id.news_detail);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            newsId = bundle.getInt("newsId");
            resId = bundle.getInt("restaurant_id");
        }

        resName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(news_res.this ,res_detail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", resId);
                intent.putExtras(bundle);
                news_res.this.startActivity(intent);
            }
        });



        newsDetail();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton news_return = (ImageButton) findViewById(R.id.news_return);
        news_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void newsDetail() {
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<record> call = myAPIService.getnewsDetail();

        call.enqueue(new Callback<record>() {
            @Override
            public void onResponse(Call<record> call, Response<record> response) {
                for(int i = 0; i < response.body().getRecordList().size(); i++){
                    if(newsId == response.body().getRecordList().get(i).getFields().getNews_id()){
                        newsTopic.setText(response.body().getRecordList().get(i).getFields().getNews_topic());
                        resName.setText(response.body().getRecordList().get(i).getFields().getEditor_res());
                        newsDate.setText(response.body().getRecordList().get(i).getFields().getNews_create());
                        newsContent.setText(response.body().getRecordList().get(i).getFields().getNews_content());

                    }
                }

            }

            @Override
            public void onFailure(Call<record> call, Throwable t) {
            }
        });
    }

}

