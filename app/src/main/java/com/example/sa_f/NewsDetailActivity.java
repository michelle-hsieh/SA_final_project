package com.example.sa_f;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        ImageButton news_detailreturn = (ImageButton) findViewById(R.id.news_detailreturn);

        if(getSupportActionBar() != null) //hide action bar
            getSupportActionBar().hide();

        //back to the login page
        news_detailreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
