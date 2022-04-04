package com.example.sa_f;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsActivity extends AppCompatActivity {

    private SimpleAdapter adapter;
    private ListView lv;

    String title[] = {"13週年慶 Part Ⅱ：蛋糕新品嚐鮮9折", "13週年慶 Part Ⅰ：1飲品＋1餐食 總金額8折", "5/01~6/30黑卡全新優惠"};
    String date[] = {"JUN 11TH, 2019", "JUN 3RD, 2019", "APR 30TH, 2019"};
    String content[] = {"過生日當然要有蛋糕慶祝才應景！千呼萬喚、眾所期待的蛋糕新品來囉～使用...",
                        "說到路易莎的味道您會想到什麼呢？「咖啡伴隨烤麵包香」相信是許多路易莎迷的...",
                        "5/01-6/30黑卡祭出尋豆師新手沖、口袋歐姆蛋全系列、小農鮮奶咖啡、大師精選濾掛咖啡..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ImageButton newsreturn = (ImageButton) findViewById(R.id.newsreturn);

        if(getSupportActionBar() != null) //hide action bar
            getSupportActionBar().hide();

        //back to the login page
        newsreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv = (ListView) findViewById(R.id.listView2);
        CustomAdapter customAdapter = new CustomAdapter();
        lv.setAdapter(customAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NewsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.news_view_content, null);
            TextView tv_newstitle = (TextView) convertView.findViewById(R.id.tv_newstitle);
            TextView tv_newsdate = (TextView) convertView.findViewById(R.id.tv_newsdate);
            TextView tv_newscontent = (TextView) convertView.findViewById(R.id.tv_newscontent);

            tv_newstitle.setText(title[position]);
            tv_newsdate.setText(date[position]);
            tv_newscontent.setText(content[position]);

            return convertView;
        }
    }
}
