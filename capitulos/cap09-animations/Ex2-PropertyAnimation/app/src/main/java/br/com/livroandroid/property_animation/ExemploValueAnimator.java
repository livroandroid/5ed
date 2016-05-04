package br.com.livroandroid.property_animation;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ExemploValueAnimator extends AppCompatActivity {
    private boolean visivel = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View view) {
        final ImageView img = (ImageView) findViewById(R.id.img);
        // Animacão genérica de 1 até 0
        ValueAnimator a = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.anim.animator_1_para_0);
        a.setTarget(img);
        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                // Fica ouvindo os valores durante a Animacão
                Float valor = (Float) animation.getAnimatedValue();
                // Altera o alpha
                img.setAlpha(valor);
            }
        });
        animar(a);
    }

    public void onClickAnimarAPI(View view) {
        final ImageView img = (ImageView) findViewById(R.id.img);
        // Animacão genérica de 1 até 0
        ValueAnimator a = ValueAnimator.ofFloat(1, 0);
        a.setDuration(10000);
        a.setTarget(img);
        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                // Fica ouvindo os valores durante a Animacão
                Float valor = (Float) animation.getAnimatedValue();
                Log.d("livro", "onAnimationUpdate : " + valor);
                // Altera o alpha
                img.setAlpha(valor);
            }
        });
        animar(a);
    }

    private void animar(ValueAnimator anim) {
        anim.setDuration(2000);
        if (visivel) {
            anim.start();
        } else {
            // Apenas reverte a Animacão
            anim.reverse();
        }
        // Inverte o flag para na próxima vez utilizar a Animacão inversa
        visivel = !visivel;
    }
}
