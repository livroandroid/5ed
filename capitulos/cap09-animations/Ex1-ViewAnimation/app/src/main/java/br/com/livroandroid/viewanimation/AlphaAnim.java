package br.com.livroandroid.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AlphaAnim extends AppCompatActivity {
    private boolean visivel = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View v) {
        int anim = visivel ? android.R.anim.fade_out : android.R.anim.fade_in;
        Animation a = AnimationUtils.loadAnimation(this, anim);
        animation(a);
    }

    public void onClickAnimarAPI(View v) {
        AlphaAnimation fade_out = new AlphaAnimation(1.0f, 0.0f);
        AlphaAnimation fade_in = new AlphaAnimation(0.0f, 1.0f);
        AlphaAnimation a = visivel ? fade_out : fade_in;
        animation(a);
    }

    public void animation(Animation a) {
        // 2 segundos
        a.setDuration(2000);
        // Manter o efeito no final da animação
        a.setFillAfter(true);
        // Inicia a animação
        ImageView img = (ImageView) findViewById(R.id.img);
        img.startAnimation(a);
        // Inverte o flag para na próxima vez utilizar a animação inversa
        visivel = !visivel;
    }
}
