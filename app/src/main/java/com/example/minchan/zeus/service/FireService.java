package com.example.minchan.zeus.service;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class FireService extends Service {

    public class FireServiceBinder extends Binder {
        IBinder getService() {
            return FireServiceBinder.this;
        }
    }

    private IBinder mBinder = new FireServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        return super.onStartCommand(intent, flag, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
// 이거도 마찬가지