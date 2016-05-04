package br.com.livroandroid.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Exemplo de Handler com o método postDelayed(runnable)
 */
public class DemoHandlerRunnableActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_demo_handler_message);
        findViewById(R.id.btEnviar).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria a mensagem com delay de 3 seg
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "A mensagem chegou com Runnable!",Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });
    }
}

