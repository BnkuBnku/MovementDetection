package com.example.movementdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView sensorText, Message;
    Sensor sensor;
    SensorManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorText = findViewById(R.id.text);
        Message = findViewById(R.id.textView);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        manager.registerListener(sensorEL, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    SensorEventListener sensorEL = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            sensorText.setText("X: " + event.values[0] + "\n"+
                                "Y: " + event.values[1] + "\n"+
                                "Z: " + event.values[2] + "\n");

            if(event.values[0]>8 || event.values[1]>8 || event.values[2]>8){
                Message.setText("TOO MUCH AXIS");
            }
            else{
                Message.setText("");
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}