package br.com.livroandroid.viewanimation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


public class ExemploDrawableAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_drawable_animation);

        ImageView img = (ImageView) findViewById(R.id.img);
        Animatable anim = (AnimationDrawable) img.getDrawable();
        anim.start();
    }
}
