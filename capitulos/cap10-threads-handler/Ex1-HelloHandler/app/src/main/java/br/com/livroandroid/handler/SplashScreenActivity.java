package br.com.livroandroid.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Exibe o layout com a imagem...
        setContentView(R.layout.activity_splash_screen);
        // delay de 1 segundo
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia a MainActivity
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                // Fecha a activity da splash
                finish();
            }
        },2000);
    }
}
