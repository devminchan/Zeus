package com.example.minchan.zeus.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.minchan.zeus.R;

//import com.example.minchan.zeus.R;

public class IntroActivity extends AppCompatActivity{

    private ImageView IVlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        IVlogo = (ImageView)findViewById(R.id.IVlogo);

        try{
            //3초간 정지
            Thread.sleep(3000);
        } catch(Exception e){
            e.printStackTrace();
        }

        //intent 만들어서 새 화면 띄움
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

    }
}
