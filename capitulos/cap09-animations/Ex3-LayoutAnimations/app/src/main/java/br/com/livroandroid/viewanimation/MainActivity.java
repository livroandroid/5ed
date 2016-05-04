package br.com.livroandroid.viewanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String[] ops = new String[]{
            "Anim Layout Changes",
            "Sair"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        ListView listView = new ListView(this);
        setContentView(listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ops));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, ExemploAnimLayoutChanges.class));
                break;
            default:
                finish();
        }
    }
}
