package com.example.minchan.zeus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.minchan.zeus.R;

public class IntroActivity extends AppCompatActivity implements Runnable{

    private ImageView IVlogo = null;
    private Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        IVlogo = findViewById(R.id.IVlogo);
        handler = new Handler();
        handler.postDelayed(this, 2000);
    }

    @Override
    public void run() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.face_out, R.anim.face_in);
    }
}
