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

import com.loopj.android.http.HttpGet;

import org.json.JSONObject;

import java.net.HttpURLConnection;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class RegisterActivity extends AppCompatActivity {
    static boolean isExisted = false;
    static EditText et_regAcc, et_regName, et_retPw;
    static String regAcc, regName, regPw;
    static int err;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_reg = (Button) findViewById(R.id.btn_reg);
        ImageButton ib_return = (ImageButton) findViewById(R.id.ib_return);

        if(getSupportActionBar() != null) //hide action bar
            getSupportActionBar().hide();

        //back to the login page
        ib_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_regAcc = (EditText) findViewById(R.id.et_regAcc);
                et_regName = (EditText) findViewById(R.id.et_regName);
                et_retPw = (EditText) findViewById(R.id.et_regPw);
                regAcc = et_regAcc.getText().toString();
                regName = et_regName.getText().toString();
                regPw = et_retPw.getText().toString();

                if(et_regAcc.getText().toString().length() == 0 ||
                   et_regName.getText().toString().length() == 0 ||
                   et_retPw.getText().toString().length() == 0)
                {
                    et_regAcc.setText(null);
                    et_regName.setText(null);
                    et_retPw.setText(null);
                    et_regAcc.requestFocus();
                    Toast.makeText(RegisterActivity.this, "欄位不得為空，請重新輸入", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                String url = "https://api.airtable.com/v0/appwcA0s1PWNuKBw8/Customer";
                                HttpClient mHttpClient = new DefaultHttpClient();

                                HttpGet mHttpGet = new HttpGet(url);
                                mHttpGet.addHeader("Authorization", "Bearer keyQgefWhH0oVJRvQ");
                                HttpResponse response = mHttpClient.execute(mHttpGet);
                                HttpEntity resEntity = response.getEntity();
                                String result = EntityUtils.toString(resEntity);
                                JSONObject cus = new JSONObject(result); //convert string to JSONObject

                                for(int i = 0; i < cus.optJSONArray("records").length(); i++)
                                {
                                    if(regAcc.equals(cus.optJSONArray("records").optJSONObject(i).optJSONObject("fields").optString("cus_acc")))
                                    {
                                        isExisted = true;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Stuff that updates the UI
                                                et_regAcc.setText(null);
                                                et_regName.setText(null);
                                                et_retPw.setText(null);
                                                et_regAcc.requestFocus();
                                                Toast.makeText(RegisterActivity.this, "帳號無效，請重新輸入", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                    }
                                }

                                if(!isExisted) //if the account haven't been registered
                                {
                                    HttpPost mHttpPost = new HttpPost(url);
                                    mHttpPost.addHeader("Authorization", "Bearer keyQgefWhH0oVJRvQ");
                                    mHttpPost.addHeader("Content-Type", "application/json");

                                    JSONObject reg = new JSONObject();
                                    JSONObject obj = new JSONObject();
                                    reg.put("cus_acc", regAcc);
                                    reg.put("cus_name", regName);
                                    reg.put("cus_pw", regPw);
                                    obj.put("fields", reg);
                                    String entity = new String(obj.toString());
                                    mHttpPost.setEntity(new StringEntity(obj.toString()));

                                    HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
                                    String code = mHttpResponse.getEntity().toString();

                                    if (mHttpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                                        String mJsonText = EntityUtils.toString(mHttpResponse.getEntity());

                                        GlobalVariables.acc = regAcc;
                                        GlobalVariables.pw = regPw;
                                        GlobalVariables.isVisited = true;

                                        //back to homepage
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        err = mHttpResponse.getStatusLine().getStatusCode();
                                    }
                                }
                            } catch (Exception e) {
                                Log.d("error: ", e.toString());
                            }
                        }
                    }).start();
                }
            }
        });
    }
}
