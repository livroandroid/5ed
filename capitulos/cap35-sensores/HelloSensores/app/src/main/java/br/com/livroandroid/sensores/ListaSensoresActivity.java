package br.com.livroandroid.sensores;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Exibe os sensores disponiveis
 *
 * @author rlecheta
 */
public class ListaSensoresActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private SensorManager sensorManager;
    private List<Sensor> sensorList;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_lista_sensores);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<String> nomes = new ArrayList<>();
        for (Sensor s : sensorList) {
            nomes.add(s.getName() + " - " + s.getVendor() + " - " + s.getType());
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, layout, nomes);
        listView.setAdapter(adaptador);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Sensor sensor = sensorList.get(position);
        String msg = sensor.getName() + " - " + sensor.getVendor();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        // Vai para tela que vai ligar este sensor
        Intent intent = new Intent(this, TestSensorActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}