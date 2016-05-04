package br.com.livroandroid.helloactivitytransition;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPlaneta(View view) {
        Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);

        Pair p1 = Pair.create(findViewById(R.id.img), getString(R.string.img_transition_key));
        Pair p2 = Pair.create(findViewById(R.id.img), getString(R.string.title_transition_key));
        ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2);
        ActivityCompat.startActivity(this, intent, opts.toBundle());
    }
}
