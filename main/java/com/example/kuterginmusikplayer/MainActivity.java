package com.example.kuterginmusikplayer;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Intent i;
    TextView textView;
     SensorManager sensorManager;
     Sensor sensorLight;
     List<Sensor> sensors;

     SensorEventListener listener = new SensorEventListener() {
         @Override
         public void onSensorChanged(SensorEvent event) {
             textView.setText(String.valueOf(event.values[0]));
         }

         @Override
         public void onAccuracyChanged(Sensor sensor, int accuracy) {

         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(this, MyService.class);
        textView = findViewById(R.id.textview);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    public void onMyStop(View view) {
        startService(i);
        sensorManager.registerListener(listener,sensorLight,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onMyStart(View view) {
        stopService(i);
        StringBuilder sb = new StringBuilder();
        for (Sensor sensor :
                sensors) {
            sb.append("Name ").append(sensor.getName()).append(", type ").append(sensor.getType()).append("\n=====================\n");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Запуск париложения", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
