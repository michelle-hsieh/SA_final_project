package com.example.sa_f;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sa_f.database.Infor;
import com.example.sa_f.database.MyAPIService;
import com.example.sa_f.database.RetrofitManager;
import com.example.sa_f.database.diaryPic;
import com.example.sa_f.database.record;
import com.example.sa_f.profile_related.DiaryItemAdapter;
import com.example.sa_f.profile_related.DiaryItems;
import com.example.sa_f.profile_related.addNewDiary;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDiary extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DiaryItemAdapter adapter;
    MyAPIService myAPIService;
    View view;
    boolean is_edit;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    i = new Intent(MyDiary.this, MainActivity.class);
                    startActivity(i);
                    return true;

                case R.id.navigation_dashboard:
                    i = new Intent(MyDiary.this, Blank.class);
                    startActivity(i);
                    return true;

                case R.id.navigation_notifications:
                    i = new Intent(MyDiary.this, Profile.class);
                    startActivity(i);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        BottomNavigationView navView = findViewById(R.id.nav_view2);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //新增美食日記
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyDiary.this, addNewDiary.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //返回 事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //讀取資料
        myAPIService = RetrofitManager.getInstance().getAPI();
        final Call<record> call = myAPIService.getPersonalDiary();

        call.enqueue(new Callback<record>() {
            @Override
            public void onResponse(Call<record> call, Response<record> response) {
                load(response.body().getRecordList()); //response.body() 就是  record
            }

            @Override
            public void onFailure(Call<record> call, Throwable t) {
                Toast.makeText(MyDiary.this, "網路異常，請重新再試! ", Toast.LENGTH_SHORT).show();
            }
        });

        final TextView edit = findViewById(R.id.mydiary_edit);
        is_edit = false;
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if (!is_edit) {
                    edit.setText("完成");
                    is_edit = true;
                }
                else {
                    edit.setText("編輯");
                    is_edit = false;
                }
                adapter.changeState(is_edit);
                /*
                trash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.
                        intent intent = new Intent(MyDiary.this, DiaryItemAdapter.class);
                        intent.putExtra("diary_id", )
                        if (res_id != 0) {
                            myAPIService.deleteInfor(res_id + "");
                        }
                    }

                });*/

            }
        });
    }

    @SuppressLint("RestrictedApi")
    public void load(ArrayList<Infor> list) {
        final ArrayList<DiaryItems> diary_list = new ArrayList<>();

        Intent intent = getIntent();
        int res_id = intent.getIntExtra("res_id", 0);
        String cus_id = GlobalVariables.id;

        TextView diary_title = findViewById(R.id.mydiary_title);

        int test = 0; //0 : 個人美食日記, 1 : 餐廳美食日記

        //個人美食日記
        if (res_id == 0 && cus_id != "") {
            diary_title.setText("我的美食日記");
            test = 0;
        }
        //餐廳美食日記
        else if (res_id != 0) {
            diary_title.setText("餐廳美食日記");
            findViewById(R.id.mydiary_edit).setVisibility(View.GONE);
            test = 1;
        }

        for (int i = 0; i < list.size(); ++i) {
            switch (test) {
                case 1:
                    int[] r_id = list.get(i).getFields().getDiary_resId();
                    String share = list.get(i).getFields().getIs_share();
                    if (r_id == null || r_id[0] != res_id || share.equals("否")) {
                        continue;
                    }
                    break;
                case 0:
                    String[] c_id = list.get(i).getFields().getDiary_editor();
                    if (c_id == null || !c_id[0].equals(cus_id)) {
                        continue;
                    }
                    break;
                default:
                    break;
            }

            //print
            diaryPic[] dps = list.get(i).getFields().getDiary_pic();
            String url = "error";
            if (dps != null) url = dps[0].getUrl();
            diary_list.add(new DiaryItems(url,
                    list.get(i).getFields().getDiary_topic(),
                    list.get(i).getFields().getDiary_content(),
                    list.get(i).getFields().getDiary_id(),
                    list.get(i).getFields().getDiary_resId()));
        }

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new DiaryItemAdapter(MyDiary.this, diary_list);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));//1 row 1 cols
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DiaryItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DiaryItems item = diary_list.get(position);

                Intent intent = new Intent();
                intent.setClass(MyDiary.this, Diary_item.class);
                Bundle bundle = new Bundle();
                bundle.putInt("diary_id", item.getDiary_id());
                bundle.putInt("res_id", item.getDiary_resId());
                intent.putExtras(bundle);
                MyDiary.this.startActivity(intent);
                /*

                Intent intent = new Intent(MyDiary.this, Blank.class);
                Toast.makeText(MyDiary.this, item.getDiaryTopic(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
                */

            }
        });
    }
}