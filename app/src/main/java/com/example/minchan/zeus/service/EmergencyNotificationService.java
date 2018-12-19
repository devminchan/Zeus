package com.example.minchan.zeus.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.minchan.zeus.R;
import com.example.minchan.zeus.activity.FireEvacuActivity;
import com.example.minchan.zeus.activity.NotifyActivity;

public class EmergencyNotificationService extends Service {

    public final class Actions {
        public static final String FIRE = "cmm:FIRE";
        public static final String EARTH_QUAKE = "cmm:EARTH_QUAKE";
        private Actions() { }
    }

    private BroadcastReceiver notifyReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        notifyReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                displayNotification(action);
            }

            public void displayNotification(String actionName)
            {
                Intent intent = new Intent(getApplicationContext(), NotifyActivity.class);
                intent.putExtra("SITUATION", actionName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("LOG", EmergencyNotificationService.this.getClass().getName() + " Started");

        runForeground(startId);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Actions.FIRE);
        filter.addAction(Actions.EARTH_QUAKE);

        LocalBroadcastManager.getInstance(EmergencyNotificationService.this)
                .registerReceiver(notifyReceiver, filter);

        return super.onStartCommand(intent, flags, startId);
    }

    private void runForeground(int startId) {
        startForeground(1, new Notification());

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(getApplicationContext())
                                        .setContentTitle("")
                                        .setContentText("")
                                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                                        .build();
        nm.notify(startId, notification);
        nm.cancel(startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (notifyReceiver != null) {
            LocalBroadcastManager.getInstance(EmergencyNotificationService.this)
                    .unregisterReceiver(notifyReceiver);
        }
    }
}
