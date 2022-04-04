package com.example.sa_f;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

import com.example.sa_f.fragment_f.DiaryFragment;
import com.example.sa_f.fragment_f.HotFragment;
import com.example.sa_f.fragment_f.NewsFragment;
import com.example.sa_f.fragment_f.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    /*
                    i = new Intent(MainActivity.this, Blank.class);
                    startActivity(i);*/
                    return true;
                case R.id.navigation_dashboard:
                    if(GlobalVariables.acc == "" || GlobalVariables.pw == ""){
                        i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                        return true;
                    }
                    else{
                        i = new Intent(MainActivity.this, Blank.class);
                        startActivity(i);
                        return true;
                    }

                case R.id.navigation_notifications:
                    if(GlobalVariables.acc == "" || GlobalVariables.pw == ""){
                        i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                        return true;
                    }
                    else{
                        i = new Intent(MainActivity.this, Profile.class);
                        startActivity(i);
                        return true;
                    }

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!GlobalVariables.isVisited) //判斷有沒有跑到登入的介面過
        {
            Intent intent = new Intent(com.example.sa_f.MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NewsFragment newsFragment = new NewsFragment();
        HotFragment hotFragment = new HotFragment();
        DiaryFragment diaryFragment = new DiaryFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.AddFragment(newsFragment ,"最新消息");
        viewPagerAdapter.AddFragment(hotFragment ,"熱門推薦");
        viewPagerAdapter.AddFragment(diaryFragment ,"最新食記");

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        BottomNavigationView navView = findViewById(R.id.bottom);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i;
        //noinspection SimplifiableIfStatement
        if (id == R.id.site) {
            i = new Intent(MainActivity.this, Blank.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;


        if (id == R.id.nav_login) {
            // Handle the camera action
            if(GlobalVariables.acc == "" || GlobalVariables.pw == ""){
                i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
            }
            else{
                item.setVisible(false);
                return true;
            }
        }
        if (id == R.id.nav_place) {
            i = new Intent(MainActivity.this, choiceplace.class);
            startActivity(i);

        } else if (id == R.id.nav_fav) {
            if(GlobalVariables.acc == "" || GlobalVariables.pw == ""){
                i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
            }
            else{
                i = new Intent(MainActivity.this, MyFavActivity.class);
                startActivity(i);
                return true;
            }
            /*
            i = new Intent(MainActivity.this, MyFavActivity.class);
            startActivity(i);
            */

        } else if (id == R.id.nav_site_search) {
            i = new Intent(MainActivity.this, Blank.class);
            startActivity(i);

        } else if (id == R.id.nav_history) {
            if(GlobalVariables.acc == "" || GlobalVariables.pw == ""){
                i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
            }
            else{
                i = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(i);
                return true;
            }
            /*
            i = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(i);
            */

        } else if (id == R.id.nav_diary) {
            if(GlobalVariables.acc == "" || GlobalVariables.pw == ""){
                i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
            }
            else{
                i = new Intent(MainActivity.this, MyDiary.class);
                startActivity(i);
                return true;
            }


        } else if (id == R.id.nav_j) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        }else if (id == R.id.nav_k) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_a) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_c) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_h) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_t) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_v) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_e) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        }else if (id == R.id.nav_pot){
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_s) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_va) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_com) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_after) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_morn) {
            i = new Intent(MainActivity.this, CateActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
