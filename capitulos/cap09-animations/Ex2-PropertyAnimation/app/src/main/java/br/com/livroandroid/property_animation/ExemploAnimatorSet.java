package br.com.livroandroid.property_animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class ExemploAnimatorSet extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View view) {
        ImageView img = (ImageView) findViewById(R.id.img);
        AnimatorSet lista = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.animator_set);
        lista.setTarget(img);
        animar(lista);
    }

    public void onClickAnimarAPI(View view) {
        ImageView img = (ImageView) findViewById(R.id.img);
        float y = img.getY();
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
        ObjectAnimator translateAnim = ObjectAnimator.ofFloat(img, "y", y, img.getHeight() * 2);
        // Fazemos o reverse manual aqui
        alphaAnim.setRepeatCount(1);
        alphaAnim.setRepeatMode(Animation.REVERSE);
        translateAnim.setRepeatCount(1);
        translateAnim.setRepeatMode(Animation.REVERSE);
        AnimatorSet lista = new AnimatorSet();
        lista.playTogether(translateAnim, alphaAnim);
        animar(lista);
    }

    private void animar(AnimatorSet lista) {
        lista.setDuration(2000);
        lista.start();
    }
}
