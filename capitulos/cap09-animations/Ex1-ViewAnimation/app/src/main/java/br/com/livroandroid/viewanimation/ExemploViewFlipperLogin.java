package br.com.livroandroid.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class ExemploViewFlipperLogin extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_animado);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button bLogin = (Button) findViewById(R.id.btLogin);
        bLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                proximaView();
            }
        });
    }

    private void proximaView() {
        ViewFlipper flip = (ViewFlipper) findViewById(R.id.flip);
        // animação de saída da view atual
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        out.setDuration(2000);
        // animação de entrada da próxima view
        Animation in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        in.setDuration(2000);
        // Configura a animação de entrada e saída
        flip.setInAnimation(in);
        flip.setOutAnimation(out);
        // Troca para a próxima view
        flip.showNext();
    }
}
