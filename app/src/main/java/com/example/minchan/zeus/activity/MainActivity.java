package com.example.minchan.zeus.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.minchan.zeus.R;
import com.example.minchan.zeus.service.EarthQuakeService;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // 추가된 소스
    private Toolbar tbMain;
    private ListView lvMainDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        // 추가된 소스, Toolbar를 생성한다.
        tbMain = findViewById(R.id.tb_main);
        mDrawerLayout = findViewById(R.id.dlMain);
        tbMain.setTitle("");

        // 왼쪽에 툴바 추가하는 코드 wkadh
        tbMain.setNavigationIcon(R.drawable.menu);
        setSupportActionBar(tbMain);
        tbMain.setNavigationOnClickListener(
                (view) -> {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.open, R.string.close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.first: {
                Toast.makeText(this, "수지니 사랑행!!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.second: {
                Intent intent = new Intent(MainActivity.this, FireInfoActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.third: {
                Intent intent = new Intent(MainActivity.this, EarthQuakeService.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        return false;
    }
}
