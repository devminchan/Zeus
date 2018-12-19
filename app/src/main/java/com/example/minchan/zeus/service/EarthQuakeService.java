package com.example.minchan.zeus.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.example.minchan.zeus.R;

public class EarthQuakeService extends Service implements SensorEventListener, Runnable {

    private SensorManager mSensorManager;
    private Sensor mAccSensor;

    private boolean isCached = false;

    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;

    private static final int SHAKE_THRESHOLD = 1200;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        runForeground(startId);

        if (mSensorManager != null && mAccSensor != null) {
            mSensorManager.registerListener(this, mAccSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(getApplicationContext(), "EarthQuakeService doesn't run", Toast.LENGTH_SHORT).show();
        }

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

        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isCached)
            return;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();;
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    // 이벤트발생!!
                    Toast.makeText(getApplicationContext(), "흔들림 감지!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent();
                    intent.setAction(EmergencyNotificationService.Actions.EARTH_QUAKE);
                    LocalBroadcastManager.getInstance(EarthQuakeService.this).sendBroadcast(intent);

                    Thread t = new Thread();
                    t.run();
                }

                lastX = event.values[0];
                lastY = event.values[1];
                lastZ = event.values[2];
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void run() {
        isCached = true;

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isCached = false;
    }
}