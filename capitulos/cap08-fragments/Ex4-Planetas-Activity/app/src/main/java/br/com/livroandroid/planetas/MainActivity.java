package br.com.livroandroid.planetas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new PlanetaAdapter(this));
        listView.setOnItemClickListener(onItemClickPlaneta());
    }

    private AdapterView.OnItemClickListener onItemClickPlaneta() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanetaAdapter adapter = (PlanetaAdapter) parent.getAdapter();
                String planeta = (String) adapter.getItem(position);
                Toast.makeText(getBaseContext(), "Planeta: " + planeta, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);
                intent.putExtra("planeta", planeta);
                startActivity(intent);
            }
        };
    }
}
