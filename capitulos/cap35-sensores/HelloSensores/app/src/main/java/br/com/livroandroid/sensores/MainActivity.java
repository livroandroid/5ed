package br.com.livroandroid.sensores;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Exemplo de sensores
 *
 * @author rlecheta
 */

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        String[] items = new String[]{
                "Listar Sensores",
                "Luminosidade",
                "Pressao",
                "Proximidade",
                "Shake",
                "Acelerômetro",
                "Acelerômetro Jogo/Boneco",
                "Giroscopio",
                "Google Fit",
                "Sair"};

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    startActivity(new Intent(this, ListaSensoresActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(this, LuminosidadeActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(this, PressaoActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(this, ProximidadeActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(this, ShakeActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(this, AcelerometroActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(this, AcelerometroJogoActivity.class));
                    break;
                case 7:
                    startActivity(new Intent(this, GiroscopioActivity.class));
                    break;
                case 8:
                    startActivity(new Intent(this, GoogleFitPedometroActivity.class));
                    break;
                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}