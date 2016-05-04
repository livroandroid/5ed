package br.com.livroandroid.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Exemplo de Handler com o método sendMessageDelayed(msg)
 */
public class DemoHandlerMessageActivity extends AppCompatActivity {

    protected static final int MENSAGEM_TESTE = 1;
    private Handler handler = new TesteHandler();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_demo_handler_message);
        findViewById(R.id.btEnviar).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria a mensagem com delay de 3 seg
                Message msg = new Message();
                msg.what = MENSAGEM_TESTE;
                // Envia a mensagem
                handler.sendMessageDelayed(msg, 3000);
            }
        });
    }

    /**
     * Handler utilizado para receber a mensagem *
     */
    private class TesteHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // O atributo msg.what permite identificar a mensagem
            switch (msg.what) {
                case MENSAGEM_TESTE:
                    Toast.makeText(getBaseContext(), "A mensagem chegou!",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}

