package br.com.livroandroid.property_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ExemploAnimatorListenerApagarTela extends AppCompatActivity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btOk = (Button) findViewById(R.id.btLogin);
        btOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Simular login aqui... Se login ok, aplicar a animaçãoo e trocar de tela
        final ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
        layout.animate().x(1).y(2).alpha(0);
        ValueAnimator anim = ObjectAnimator.ofFloat(layout, "alpha", 1f, 0f);
        anim.setDuration(2000);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }
        });
        anim.start();
    }
}
