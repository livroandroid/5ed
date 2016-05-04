package br.com.livroandroid.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Ricardo Lecheta
 */
public class GiroscopioActivity extends AppCompatActivity implements SensorEventListener {

    private static final int TIPO_SENSOR = Sensor.TYPE_GYROSCOPE;
    private SensorManager sensorManager;
    private Sensor sensor;
    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_acelerometro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(TIPO_SENSOR) != null) {
            sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
        } else {
            Toast.makeText(this, "Sensor TYPE_GYROSCOPE não disponível.", Toast.LENGTH_SHORT).show();
        }


        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // SENSOR_DELAY_NORMAL = delay 200.000 microsegundos
        // SENSOR_DELAY_UI = delay 60.000 microsegundos
        // SENSOR_DELAY_GAME = delay 20.000 microsegundos
        // SENSOR_DELAY_FASTEST = delay 0.0 microsegundos
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Mudou o status de precisão do sensor
//		SENSOR_STATUS_ACCURACY_LOW, SENSOR_STATUS_ACCURACY_MEDIUM, SENSOR_STATUS_ACCURACY_HIGH, or SENSOR_STATUS_UNRELIABLE.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float sensorX = 0;
        float sensorY = 0;
        float sensorZ = 0;
        String debugText = "";

        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                sensorX = -event.values[0];
                sensorY = event.values[1];
                sensorZ = event.values[2];
                debugText = "Rotação 0º";
                break;
            case Surface.ROTATION_90:
                sensorX = event.values[1];
                sensorY = event.values[0];
                sensorZ = event.values[2];
                debugText = "Rotação 90º";
                break;
            case Surface.ROTATION_180:
                sensorX = event.values[0];
                sensorY = -event.values[1];
                sensorZ = event.values[2];
                debugText = "Rotação 180º";
                break;
            case Surface.ROTATION_270:
                sensorX = -event.values[1];
                sensorY = -event.values[0];
                sensorZ = event.values[2];
                debugText = "Rotação 270º";
                break;
        }

        ((TextView) findViewById(R.id.tX)).setText("X: " + sensorX);
        ((TextView) findViewById(R.id.tY)).setText("Y: " + sensorY);
        ((TextView) findViewById(R.id.tZ)).setText("Z: " + sensorZ);
        ((TextView) findViewById(R.id.tMsg)).setText(debugText);
    }
}
