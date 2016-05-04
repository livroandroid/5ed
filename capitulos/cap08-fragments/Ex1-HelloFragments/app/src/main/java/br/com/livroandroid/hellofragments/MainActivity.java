package br.com.livroandroid.hellofragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Se necessário recuperar o FragmentManager para encontrar algum fragment
        FragmentManager fm = getSupportFragmentManager();

        // Se estiver adicionado no XML
        //Fragment1 frag1 = (Fragment1) fm.findFragmentById(R.id.frag1);
        //frag1.hello();

        // Adiciona o fragment dinamicamente pela API
        if (savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();
            Fragment1 frag1 = new Fragment1();
            ft.add(R.id.layoutFrag, frag1, "Fragment1");
            ft.commit();
        }

    }
}
