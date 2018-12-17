package com.example.minchan.zeus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.minchan.zeus.R;

//import com.example.minchan.zeus.R;

public class IntroActivity extends AppCompatActivity{

    private ImageView IVlogo = null;
    private Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
       try{
           Thread.sleep(3000);
       }catch(Exception e){
           e.printStackTrace();
       }
       Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
       startActivity(intent);
    }
}
