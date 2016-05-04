package br.com.livroandroid.helloactivitytransition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PlanetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planeta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void finish() {
        super.finish();
        // Para voltar utiliza a animacao da esquerda para a direita
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}
