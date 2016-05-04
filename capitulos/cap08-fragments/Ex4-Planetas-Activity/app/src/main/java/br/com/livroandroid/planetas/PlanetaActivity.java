package br.com.livroandroid.planetas;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class PlanetaActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planeta);

        String planeta = getIntent().getStringExtra("planeta");

        TextView text = (TextView) findViewById(R.id.text);
        text.setText(planeta);

        getSupportActionBar().setTitle(planeta);
    }
}
