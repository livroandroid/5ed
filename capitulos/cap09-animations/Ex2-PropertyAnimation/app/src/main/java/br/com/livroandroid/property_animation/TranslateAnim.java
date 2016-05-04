package br.com.livroandroid.property_animation;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class TranslateAnim extends AppCompatActivity {
    private boolean flag = true;
    private ObjectAnimator a;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View view) {
        ImageView img = (ImageView) findViewById(R.id.img);
        ObjectAnimator a = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.anim.mover_baixo);
        a.setTarget(img);
        animar(a);
    }

    public void onClickAnimarAPI(View view) {
        ImageView img = (ImageView) findViewById(R.id.img);
        a = ObjectAnimator.ofFloat(img, "y", img.getHeight() / 2, img.getHeight() * 2);
        animar(a);
    }

    private void animar(ObjectAnimator anim) {
        anim.setDuration(1000);
        if (flag) {
            anim.start();
        } else {
            // Apenas reverte a Animacão
            anim.reverse();
        }
        // Inverte o flag para na próxima vez utilizar a Animacão inversa
        flag = !flag;
    }
}
