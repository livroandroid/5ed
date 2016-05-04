package br.com.livroandroid.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class RotateAnim extends AppCompatActivity {
    private boolean flag = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View v) {
        Animation gira1 = AnimationUtils.loadAnimation(this, R.anim.rotate_gira_ponta_cabeca);
        Animation gira2 = AnimationUtils.loadAnimation(this, R.anim.rotate_gira_ponta_cabeca_retorno);
        Animation a = flag ? gira1 : gira2;
        animation(a);
    }

    public void onClickAnimarAPI(View v) {
        int angulo = 180;
        Animation gira1 = new RotateAnimation(0, angulo,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5F);
        Animation gira2 = new RotateAnimation(angulo, 0,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5F);
        Animation a = flag ? gira1 : gira2;
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
        flag = !flag;
    }
}
