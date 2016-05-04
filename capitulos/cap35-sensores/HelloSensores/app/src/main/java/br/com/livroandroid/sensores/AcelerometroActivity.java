package br.com.livroandroid.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Ricardo Lecheta
 */
public class AcelerometroActivity extends AppCompatActivity implements SensorEventListener {

    private static final int TIPO_SENSOR = Sensor.TYPE_ACCELEROMETER;
    private SensorManager sensorManager;
    private Sensor sensor;
    private Display display;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_acelerometro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(TIPO_SENSOR) != null) {
            sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
        } else {
            Toast.makeText(this, "Sensor de Acelerômetro não disponível.", Toast.LENGTH_SHORT).show();
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
        long now = System.currentTimeMillis();
        if (now - time > 1000) {
            time = now;
            float values[] = SensorUtil.fixAcelerometro(this, event);
            float sensorX = values[0];
            float sensorY = values[1];
            float sensorZ = values[2];
            TextView tx = (TextView) findViewById(R.id.tX);
            TextView tY = (TextView) findViewById(R.id.tY);
            TextView tZ = (TextView) findViewById(R.id.tZ);
            TextView tMsg = (TextView) findViewById(R.id.tMsg);
            if (tx != null) {
                tx.setText("X: " + sensorX);
            }
            if (tY != null) {
                tY.setText("Y: " + sensorY);
            }
            if (tZ != null) {
                tZ.setText("Z: " + sensorZ);
            }
            if (tMsg != null) {
                tMsg.setText("Rotação: " + SensorUtil.getRotationString(this));
            }
        }
    }
}
