package com.example.sa_f;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ResLoginActivity extends AppCompatActivity {
    static String resacc, respw;
    static EditText et_resacc, et_respw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_login);

        ImageButton btn_resreturn = (ImageButton) findViewById(R.id.id_resreturn);
        Button btn_resLogin = (Button) findViewById(R.id.btn_resLogin);

        if(getSupportActionBar() != null) //hide action bar
            getSupportActionBar().hide();

        btn_resreturn.setOnClickListener(new View.OnClickListener() { //click return
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //click button restaurant login
        btn_resLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_resacc = (EditText) findViewById(R.id.et_resacc);
                et_respw = (EditText) findViewById(R.id.et_respw);
                resacc = et_resacc.getText().toString();
                respw = et_respw.getText().toString();

                String urlString = "https://api.airtable.com/v0/appwcA0s1PWNuKBw8/Restaurant?api_key=keyQgefWhH0oVJRvQ";

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(urlString, new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        boolean isLogin = false;
                        JSONArray restaurant = response.optJSONArray("records");
                        String res_acc, res_pw, is_co;
                        for(int i = 0; i < restaurant.length(); i++)
                        {
                            is_co = restaurant.optJSONObject(i).optJSONObject("fields").optString("is_co");
                            res_acc = restaurant.optJSONObject(i).optJSONObject("fields").optString("res_acc");
                            res_pw = restaurant.optJSONObject(i).optJSONObject("fields").optString("res_pw");
                            if (is_co == "是" && resacc.equals(res_acc) && respw.equals(res_pw))
                            {
                                isLogin = true;
                                GlobalVariables.isVisited = true;
                                GlobalVariables.identity = "restaurant";
                                GlobalVariables.acc = res_acc;
                                GlobalVariables.pw = res_pw;
                                break;
                            }
                        }

                        if(isLogin) //login succeed
                        {
                            Intent intent = new Intent(getApplicationContext(), Blank.class);
                            startActivity(intent);
                        }
                        else //login failed
                        {
                            et_resacc.setText(null);
                            et_respw.setText(null);
                            et_resacc.requestFocus();
                            Toast.makeText(ResLoginActivity.this, "登入失敗，請重新登入", Toast.LENGTH_SHORT).show();
                        }
                    }
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject err) {
                        Toast.makeText(ResLoginActivity.this, "Error" + statusCode + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                        //Log err message
                        Log.e("Hot Text: ", statusCode + " " + e.getMessage());
                    }
                });
            }
        });
    }
}
