package br.com.livroandroid.salvarestado;

import android.os.Bundle;

public class DemoSalvarEstadoActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_salvar_estado);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.layoutFrag, new Fragment1()).commit();
        }
    }
}
