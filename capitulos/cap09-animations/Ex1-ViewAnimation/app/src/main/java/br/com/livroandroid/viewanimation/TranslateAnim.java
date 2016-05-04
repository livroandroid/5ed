package br.com.livroandroid.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class TranslateAnim extends AppCompatActivity {
    private boolean visivel = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View v) {
        int anim = visivel ? R.anim.translate_mover_para_baixo : R.anim.translate_mover_para_cima;
        Animation a = AnimationUtils.loadAnimation(this, anim);
        animation(a);
    }

    public void onClickAnimarAPI(View v) {
        Animation moverParaBaixo = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, 2.0F
        );
        Animation moverParaCima = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, 2.0F, Animation.RELATIVE_TO_SELF, 0.0F
        );
        Animation a = visivel ? moverParaBaixo : moverParaCima;
        animation(a);
    }

    public void animation(Animation a) {
        ImageView img = (ImageView) findViewById(R.id.img);
        // 2 segundos
        a.setDuration(2000);
        // Manter o efeito no final da animação
        a.setFillAfter(true);
        img.startAnimation(a);
        // Inverte o flag para na próxima vez utilizar a animação inversa
        visivel = !visivel;
    }
}
