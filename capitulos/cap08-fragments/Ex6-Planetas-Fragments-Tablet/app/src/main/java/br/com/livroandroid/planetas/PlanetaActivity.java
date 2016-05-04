package br.com.livroandroid.planetas;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

public class PlanetaActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planeta);

        // Mostra o nome do planeta como título na action bar
        String planeta = getIntent().getStringExtra("planeta");
        getSupportActionBar().setTitle(planeta);

        if(savedInstanceState == null) {
            PlanetaFragment f = new PlanetaFragment();
            f.setArguments(getIntent().getExtras()); // Parâmetros: bundle da intent
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.layoutFrag, f, "PlanetaFragment");
            ft.commit();
        }

    }
}
