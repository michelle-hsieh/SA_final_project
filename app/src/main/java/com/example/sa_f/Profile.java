package com.example.sa_f;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.sa_f.database.Infor;
import com.example.sa_f.database.MyAPIService;
import com.example.sa_f.database.RetrofitManager;
import com.example.sa_f.database.record;
import com.example.sa_f.profile_related.ImageText;
import com.example.sa_f.profile_related.ImageTextAdapter;

import android.widget.AdapterView.*;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    private ListView listView;
    View view;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    i = new Intent(Profile.this, MainActivity.class);
                    startActivity(i);
                    return true;

                case R.id.navigation_dashboard:
                    i = new Intent(Profile.this, Blank.class);
                    startActivity(i);
                    return true;

                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navView = findViewById(R.id.bottom2);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //返回事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //姓名
        TextView cus_name = findViewById(R.id.cus_name);
        cus_name.setText(GlobalVariables.name);

        //選單
        final ArrayList<ImageText> items = new ArrayList<>();
        items.add(new ImageText(" 瀏覽紀錄", getResources().getDrawable(R.mipmap.ic_history)));
        items.add(new ImageText(" 我的帳戶", getResources().getDrawable(R.mipmap.ic_perm_identity)));
        items.add(new ImageText(" 我的收藏", getResources().getDrawable(R.mipmap.ic_favorite_border)));
        items.add(new ImageText(" 我的美食日記", getResources().getDrawable(R.mipmap.ic_bookmark_border)));
        items.add(new ImageText(" 錯誤回報", getResources().getDrawable(R.mipmap.ic_error_outline)));

        listView = findViewById(R.id.listView);
        ImageTextAdapter itAdapter = new ImageTextAdapter(this, items);
        listView.setAdapter(itAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ImageText tmp = items.get(position);
                //String tmp1 = "" + tmp.getName();

                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(Profile.this, HistoryActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(Profile.this, Blank.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(Profile.this, MyFavActivity.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(Profile.this, MyDiary.class);
                        intent4.putExtra("cus_acc", GlobalVariables.acc);
                        startActivity(intent4);
                        break;
                    case 4:
                        //Intent intent5 = new Intent(Profile.this, MyDiary.class);
                        Toast.makeText(view.getContext(), "已傳送錯誤給管理者", Toast.LENGTH_SHORT).show();
                        //startActivity(intent5);
                        break;
                    default:
                        break;
                }
            }
        });

        //登出
        Button logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariables.acc = "";
                GlobalVariables.pw = "";
                GlobalVariables.isVisited = false;
                Intent intent = new Intent(Profile.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
