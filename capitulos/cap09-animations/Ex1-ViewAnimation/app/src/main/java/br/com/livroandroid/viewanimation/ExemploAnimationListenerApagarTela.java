package br.com.livroandroid.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class ExemploAnimationListenerApagarTela extends AppCompatActivity implements OnClickListener, AnimationListener {
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
        // Simular login aqui... Se login ok, aplicar a animação e trocar de tela
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        // Define o listener para ser notificado sobre os eventos da animação
        anim.setAnimationListener(this);
        anim.setFillAfter(true);
        anim.setDuration(1500);
        layout.startAnimation(anim);
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(this, "Fim da animação", Toast.LENGTH_SHORT).show();
        // Agora podemos trocar de tela ou fazer qualquer outra coisa
        finish();
    }
}
