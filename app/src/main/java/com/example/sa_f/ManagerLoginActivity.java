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

public class ManagerLoginActivity extends AppCompatActivity {
    static String manageracc, managerpw;
    static EditText et_manageracc, et_managerpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        ImageButton btn_managerreturn = (ImageButton) findViewById(R.id.id_managerreturn);
        Button btn_managerLogin = (Button) findViewById(R.id.btn_managerLogin);

        if(getSupportActionBar() != null) //hide action bar
            getSupportActionBar().hide();

        btn_managerreturn.setOnClickListener(new View.OnClickListener() { //click return
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_managerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_manageracc = (EditText) findViewById(R.id.et_manageracc);
                et_managerpw = (EditText) findViewById(R.id.et_managerpw);
                manageracc = et_manageracc.getText().toString();
                managerpw = et_managerpw.getText().toString();

                String urlString = "https://api.airtable.com/v0/appwcA0s1PWNuKBw8/Manager?api_key=keyQgefWhH0oVJRvQ";

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(urlString, new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        boolean isLogin = false;
                        JSONArray manager = response.optJSONArray("records");
                        String manager_acc, manager_pw;
                        for(int i = 0; i < manager.length(); i++)
                        {
                            manager_acc = manager.optJSONObject(i).optJSONObject("fields").optString("manager_acc");
                            manager_pw = manager.optJSONObject(i).optJSONObject("fields").optString("manager_pw");
                            if (manageracc.equals(manager_acc) && managerpw.equals(manager_pw))
                            {
                                isLogin = true;
                                GlobalVariables.isVisited = true;
                                GlobalVariables.identity = "manager";
                                GlobalVariables.acc = manager_acc;
                                GlobalVariables.pw = manager_pw;
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
                            et_manageracc.setText(null);
                            et_managerpw.setText(null);
                            et_manageracc.requestFocus();
                            Toast.makeText(ManagerLoginActivity.this, "登入失敗，請重新登入", Toast.LENGTH_SHORT).show();
                        }
                    }
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject err) {
                        Toast.makeText(ManagerLoginActivity.this, "Error" + statusCode + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                        //Log err message
                        Log.e("Hot Text: ", statusCode + " " + e.getMessage());
                    }
                });
            }
        });
    }
}
