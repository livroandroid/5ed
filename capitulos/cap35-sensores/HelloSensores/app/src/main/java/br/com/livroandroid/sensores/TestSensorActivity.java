package br.com.livroandroid.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class TestSensorActivity extends AppCompatActivity implements SensorEventListener {
    private TextView tValor;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sensor);
        // Posição do sensor
        int position = getIntent().getIntExtra("position", 0);
        // Busca o sensor pela posição
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensor = sensorList.get(position);
        tValor = (TextView) findViewById(R.id.tValor);
        // Mostra o nome e fabricante do sensor
        getSupportActionBar().setTitle(sensor.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registra o sensor selecionado
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Cancela o registro do sensor
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Mudou o status de precisão do sensor
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Lê os valores do sensor
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < event.values.length; i++) {
            sb.append(i).append(": ").append(event.values[i]).append("\n");
        }
        // Mostra os valores
        tValor.setText(sb.toString());
    }
}
