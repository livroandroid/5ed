package br.com.livroandroid.demofragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class DemoFragment4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo_frag4);

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction t = fm.beginTransaction();

            // Fragment
            Fragment4 f = new Fragment4();

            // Args
            Intent intent = getIntent();
            Bundle args = intent.getExtras();
            f.setArguments(args);

            // Transaction
            t.add(R.id.layoutFrag, f, "Fragment4");
            t.commit();
        }
    }
}
