package br.com.livroandroid.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class ScaleAnim extends AppCompatActivity {
    private boolean visivel = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View v) {
        int anim = visivel ? R.anim.scale_diminuir : R.anim.scale_aumentar;
        Animation a = AnimationUtils.loadAnimation(this, anim);
        animation(a);
    }

    public void onClickAnimarAPI(View v) {
        ScaleAnimation encolher = new ScaleAnimation(
                1.0f, 0.0f, // X inicial e final
                1.0f, 0.0f, // Y inicial e final
                Animation.RELATIVE_TO_SELF, 0.5f, // Eixo X
                Animation.RELATIVE_TO_SELF, 0.5f  // Eixo Y
        );
        ScaleAnimation aumentar = new ScaleAnimation(
                0.0f, 1.0f, // X inicial e final
                0.0f, 1.0f, // Y inicial e final
                Animation.RELATIVE_TO_SELF, 0.5f, // Eixo X
                Animation.RELATIVE_TO_SELF, 0.5f  // Eixo Y
        );
        Animation a = visivel ? encolher : aumentar;
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
