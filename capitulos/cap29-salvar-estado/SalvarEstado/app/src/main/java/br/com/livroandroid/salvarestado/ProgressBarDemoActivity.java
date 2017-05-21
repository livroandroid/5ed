package br.com.livroandroid.salvarestado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class ProgressBarDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_demo);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.layoutFrag, new ProgressBarDemoFragment()).commit();
        }
    }
}
