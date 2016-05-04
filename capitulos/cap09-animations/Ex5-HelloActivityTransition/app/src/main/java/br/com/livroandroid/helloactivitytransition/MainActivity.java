package br.com.livroandroid.helloactivitytransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Demonstra uma animacao customizada de entrada e saida
     *
     * @param view
     */
    public void onClickPlaneta(View view) {
        Intent intent = new Intent(this, PlanetaActivity.class);

        // Fade-In e Fade-Out
        //ActivityOptionsCompat opts = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.fade_in, R.anim.fade_out);
        //ActivityCompat.startActivity(this, intent, opts.toBundle());

        // slide_in_left e slide_out_right
        ActivityOptionsCompat opts = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.slide_out_left);
        ActivityCompat.startActivity(this, intent, opts.toBundle());
    }
}
