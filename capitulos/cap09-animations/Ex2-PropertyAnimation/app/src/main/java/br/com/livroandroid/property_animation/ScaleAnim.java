package br.com.livroandroid.property_animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ScaleAnim extends AppCompatActivity {
    private boolean flag = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View view) {
        onClickAnimarAPI(view);
    }

    public void onClickAnimarAPI(View view) {
        ImageView img = (ImageView) findViewById(R.id.img);
        PropertyValuesHolder anim1 = PropertyValuesHolder.ofFloat("scaleX", 1, 0);
        PropertyValuesHolder anim2 = PropertyValuesHolder.ofFloat("scaleY", 1, 0);
        ObjectAnimator a = ObjectAnimator.ofPropertyValuesHolder(img, anim1, anim2);
        animar(a);
    }

    private void animar(ObjectAnimator anim) {
        anim.setDuration(2000);
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
