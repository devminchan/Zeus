package com.example.minchan.zeus.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class EarthQuakeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
// 이거 서비스로 돌리려면 메니페스트에 추가해야함