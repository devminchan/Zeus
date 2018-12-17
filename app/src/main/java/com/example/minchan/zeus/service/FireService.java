package com.example.minchan.zeus.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

public class FireService extends Service implements SensorEventListener {

    public class FireServiceBinder extends Binder {
        IBinder getService() {
            return FireServiceBinder.this;
        }
    }

    private IBinder mBinder = new FireServiceBinder();

    private SensorManager mSensorManager;
    private Sensor mAccSensor;
    private Sensor mMagSensor;

    private float[] m_acc_data = null;
    private float[] m_mag_data = null;

    private float[] m_rotation = new float[9];
    private float[] m_result_data = new float[9];

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mSensorManager.registerListener(this, mAccSensor, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mMagSensor, SensorManager.SENSOR_DELAY_UI);

        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccSensor = (Sensor) mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagSensor = (Sensor) mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            m_acc_data = event.values.clone();
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            m_mag_data = event.values.clone();
        }

        // 데이터가 존재하는 경우
        if (m_acc_data != null && m_mag_data != null) {
            // 가속 데이터와 자기장 데이터로 회전 매트릭스를 얻는다.
            SensorManager.getRotationMatrix(m_rotation, null, m_acc_data, m_mag_data);
            // 회전 매트릭스로 방향 데이터를 얻는다.
            SensorManager.getOrientation(m_rotation, m_result_data);
        }

        Intent intent = new Intent("zeus:fire-event");
        intent.putExtra("azimuth-me", getAzimuth(m_result_data[0]));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    private float getAzimuth(float read_azimuth) {
        float azimuth = (float) Math.toDegrees(read_azimuth);

        if (azimuth < 0)
            azimuth += 360;

        return azimuth;
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        return super.onStartCommand(intent, flag, startId);
    }

    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(this);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mSensorManager.unregisterListener(this);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        mSensorManager.registerListener(this, mAccSensor, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mMagSensor, SensorManager.SENSOR_DELAY_UI);
    }
}