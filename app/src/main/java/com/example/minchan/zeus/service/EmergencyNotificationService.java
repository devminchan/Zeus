package com.example.minchan.zeus.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class EmergencyNotificationService extends Service {

    public final class Actions {
        public static final String FIRE = "cmm:FIRE";
        public static final String EARTH_QUAKE = "cmm:EARTH_QUAKE";
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

                switch (action)
                {
                    case Actions.FIRE:

                        break;
                    case Actions.EARTH_QUAKE:

                        break;
                }
            }

            public void displayNotification()
            {
                Intent popupIntent = new Intent(/* Addition required */);
                popupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(popupIntent);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
