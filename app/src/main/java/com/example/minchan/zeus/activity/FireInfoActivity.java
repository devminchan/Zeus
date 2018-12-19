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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.minchan.zeus.R;

public class FireInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private Toolbar tbMain;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_info);

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
        tbMain.setOverflowIcon(getDrawable(R.drawable.ic_notifications_black_24dp));

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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
                break;
            }
            case R.id.second: {
                Intent intent = new Intent(FireInfoActivity.this, FireInfoActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.third: {
                Intent intent = new Intent(FireInfoActivity.this, EarthInfoActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        return false;
    }
}
