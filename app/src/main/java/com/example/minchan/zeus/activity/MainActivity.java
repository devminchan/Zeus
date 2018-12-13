package com.example.minchan.zeus.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.minchan.zeus.R;

public class MainActivity extends AppCompatActivity {
    //Zeus
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picture = (ImageView)findViewById(R.id.weathernow);
        picture.setImageDrawable(getDrawable(R.drawable.snow));
    }
}
