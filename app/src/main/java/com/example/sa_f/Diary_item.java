package com.example.sa_f;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sa_f.database.MyAPIService;
import com.example.sa_f.database.RetrofitManager;
import com.example.sa_f.database.record;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Diary_item extends AppCompatActivity {

    MyAPIService myAPIService;
    TextView diaryTopic;
    TextView diaryName;
    TextView diaryDate;
    TextView diaryContent;
    Button diaryBtu;
    int diaryId;
    int resId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_item);

        diaryTopic = (TextView) findViewById(R.id.diary_topic);
        diaryName = (TextView) findViewById(R.id.diary_name);
        diaryDate = (TextView) findViewById(R.id.diary_date);
        diaryContent = (TextView) findViewById(R.id.diary_detail);
        diaryBtu = (Button) findViewById(R.id.diary_btu);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            diaryId = bundle.getInt("diary_id");
            resId = bundle.getInt("res_id");
        }

        diaryBtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Diary_item.this ,res_detail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", resId);
                intent.putExtras(bundle);
                Diary_item.this.startActivity(intent);
            }
        });

        diaryDetail();

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

    public void diaryDetail(){
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<record> call = myAPIService.getdiaryDetail();

        call.enqueue(new Callback<record>() {
            @Override
            public void onResponse(Call<record> call, Response<record> response) {
                for(int i = 0; i < response.body().getRecordList().size(); i++){
                    if(diaryId == response.body().getRecordList().get(i).getFields().getDiary_id()){
                        diaryTopic.setText(response.body().getRecordList().get(i).getFields().getDiary_topic());
                        diaryName.setText(response.body().getRecordList().get(i).getFields().getCus_name());
                        diaryDate.setText(response.body().getRecordList().get(i).getFields().getdiaryTime());
                        diaryContent.setText(response.body().getRecordList().get(i).getFields().getDiary_content());
                        diaryBtu.setText(response.body().getRecordList().get(i).getFields().getDiary_restaurant());

                    }
                }

            }

            @Override
            public void onFailure(Call<record> call, Throwable t) {
            }
        });
    }
}
