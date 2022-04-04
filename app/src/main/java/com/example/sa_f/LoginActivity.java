package com.example.sa_f;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sa_f.database.Infor;
import com.example.sa_f.database.MyAPIService;
import com.example.sa_f.database.RetrofitManager;
import com.example.sa_f.database.record;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    static String acc, pw;
    static EditText et_acc, et_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_reslogin = (Button) findViewById(R.id.btn_reslogin);
        Button btn_managerlogin = (Button) findViewById(R.id.btn_managerlogin);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_skip = (Button) findViewById(R.id.btn_skip);
        TextView tv_register = (TextView) findViewById(R.id.tv_register);

        if(getSupportActionBar() != null) //hide action bar
            getSupportActionBar().hide();

        //click button restaurant login
        btn_reslogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResLoginActivity.class);
                startActivity(intent);
            }
        });

        //click button manager login
        btn_managerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManagerLoginActivity.class);
                startActivity(intent);
            }
        });

        //click textview register
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //click button skip
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariables.acc = "";
                GlobalVariables.pw = "";
                GlobalVariables.isVisited = true;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //click button login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_acc = (EditText) findViewById(R.id.et_acc);
                et_pw = (EditText) findViewById(R.id.et_pw);
                acc = et_acc.getText().toString();
                pw = et_pw.getText().toString();
                String urlString = "https://api.airtable.com/v0/appwcA0s1PWNuKBw8/Customer?api_key=keyQgefWhH0oVJRvQ";

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(urlString, new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        boolean isLogin = false;
                        JSONArray customer = response.optJSONArray("records");
                        String cus_acc, cus_pw, cus_id, cus_name;
                        for(int i = 0; i < customer.length(); i++)
                        {
                            cus_acc = customer.optJSONObject(i).optJSONObject("fields").optString("cus_acc");
                            cus_pw = customer.optJSONObject(i).optJSONObject("fields").optString("cus_pw");
                            cus_id = customer.optJSONObject(i).optString("id");
                            cus_name = customer.optJSONObject(i).optJSONObject("fields").optString("cus_name");
                            if (acc.equals(cus_acc) && pw.equals(cus_pw))
                            {
                                isLogin = true;
                                GlobalVariables.isVisited = true;
                                GlobalVariables.identity = "customer";
                                GlobalVariables.acc = cus_acc;
                                GlobalVariables.pw = cus_pw;
                                GlobalVariables.id = cus_id;
                                GlobalVariables.name = cus_name;
                                break;
                            }
                        }

                        if(isLogin) //login succeed
                        {
                            finish();
                        }
                        else //login failed
                        {
                            et_acc.setText(null);
                            et_pw.setText(null);
                            et_acc.requestFocus();
                            Toast.makeText(LoginActivity.this, "登入失敗，請重新登入", Toast.LENGTH_SHORT).show();
                        }
                    }
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject err) {
                        Toast.makeText(LoginActivity.this, "Error" + statusCode + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                        //Log err message
                        Log.e("Hot Text: ", statusCode + " " + e.getMessage());
                    }
                });
            }
        });
    }
}
