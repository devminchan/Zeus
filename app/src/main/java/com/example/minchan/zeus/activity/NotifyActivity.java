package com.example.minchan.zeus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minchan.zeus.R;
import com.example.minchan.zeus.service.EmergencyNotificationService.Actions;

public class NotifyActivity extends AppCompatActivity {

    private TextView title;
    private TextView content;
    private Intent executeActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        title = (TextView) findViewById(R.id.tvTitle);
        content = (TextView) findViewById(R.id.tvContent);

        Intent i = getIntent();

        switch (i.getAction())
        {
            case Actions.FIRE:
                title.setText("화재대피방법");
                content.setText("화재 발견 시 벨 울리고 '불이야!'외치기\n계단 사용하여 대피\n적신 수건 등으로 코와 입 막기\n낮은 자세로 이동");
                executeActivity = new Intent(NotifyActivity.this, FireEvacuActivity.class);
                break;
            case Actions.EARTH_QUAKE:
                title.setText("지진대피방법");
                content.setText("여진 위험으로 밖으로 이동X\n방석 등으로 머리 보호\n여진이 끝난 후 비상품 챙겨 계단 사용해 밖으로 대피");
                executeActivity = new Intent(NotifyActivity.this, FireEvacuActivity.class);
                break;
            default:
                Toast.makeText(getApplicationContext(), "잘못된 요청 형식입니다", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        executeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    public void click_yes(View view) {
        startActivity(executeActivity);
        finish();
    }

    public void click_no(View view) {
        finish();
    }
}
