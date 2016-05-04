package br.com.livroandroid.helloactivitytransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //getWindow().setEnterTransition(new Fade());
        //getWindow().setExitTransition(new Fade());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Demonstra uma animação customizada de entrada e saída
     *
     * @param view
     */
    public void onClickPlaneta(View view) {
        Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);

        // Efeito padrão de cross-fading
        //ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        //ActivityCompat.startActivity(this, intent, opts.toBundle());

        // Passando parâmetros entre activities
        ImageView img = (ImageView) findViewById(R.id.img);
        String key = getString(R.string.transition_key);
        ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(this, img, key);
        ActivityCompat.startActivity(this, intent, opts.toBundle());
    }
}
