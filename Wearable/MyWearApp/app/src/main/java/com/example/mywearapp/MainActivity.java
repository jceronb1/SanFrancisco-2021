package com.example.mywearapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.CardScrollView;
import android.view.Gravity;
import android.widget.TextView;


//Code implemented according to https://hub.packtpub.com/building-your-first-android-wear-application/ tutoriak
public class MainActivity extends Activity implements SensorEventListener {
    private TextView mTextView;
    private TextView textView;
    private TextView mTextAlarm;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);
        sensorManager = ((SensorManager)
                getSystemService(SENSOR_SERVICE));
        sensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
    }
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, this.sensor, 3);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText("" + (int) event.values[0]);
        if((int) event.values[0]>100){
            mTextAlarm.setText("Alerta Taquicardia");
        }
        else{
            if((int) event.values[0]<60){
                mTextAlarm.setText("Alerta Braquicardia");
            }
            else mTextAlarm.setText("Estado normal");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}