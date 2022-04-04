package com.example.sa_f;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class choiceplace extends AppCompatActivity {

    public String[] str = {"台北","新北","基隆","桃園","宜蘭","新竹","苗栗","台中","彰化","南投","雲林","嘉義","台南","高雄","屏東","花蓮","台東"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choiceplace);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            Button close = (Button) findViewById(R.id.closed);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(choiceplace.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            ListView listview = (ListView) findViewById(R.id.list_view);
            //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(onClickListView);

        }

        private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast 快顯功能 第三個參數 Toast.LENGTH_SHORT 2秒  LENGTH_LONG 5秒
                Toast.makeText(choiceplace.this,"點選第 "+(position +1) +" 個 \n內容："+str[position], Toast.LENGTH_SHORT).show();
            }
        };
}
