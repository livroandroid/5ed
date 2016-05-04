package br.com.livroandroid.property_animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


public class ExemploValueAnimatorTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_value_animator_text_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final TextView text = (TextView) findViewById(R.id.text);

        // Animacão genérica de 1 até 0
        ValueAnimator a = ValueAnimator.ofFloat(1, 100);
        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                // Fica ouvindo os valores durante a Animacão
                Float valor = (Float) animation.getAnimatedValue();
                Log.d("livro", "onAnimationUpdate : " + valor);
                // Altera o alpha
                text.setText(String.valueOf(valor.intValue()));
            }
        });
        a.setDuration(1000);
        a.setTarget(text);
        a.start();
    }
}
