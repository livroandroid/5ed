package br.com.livroandroid.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

/**
 * Conecta no servidor para enviar mensagens
 *
 * @author Ricardo Lecheta
 */
public class BluetoothChatClientActivity extends BluetoothCheckActivity implements ChatController.ChatListener {
    protected static final String TAG = "livroandroid";
    // Precisa utilizar o mesmo UUID que o servidor utilizou para abrir o socket servidor
    protected static final UUID uuid = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    protected BluetoothDevice device;
    protected TextView tMsg, tMsgRecebidas;
    protected ChatController chat;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_bluetooth_chat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tMsg = (TextView) findViewById(R.id.tMsg);
        tMsgRecebidas = (TextView) findViewById(R.id.tMsgRecebidas);
        // Device selecionado na lista
        device = getIntent().getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        try {
            // Faz a conexão se abriu no modo chat cliente
            if(device != null) {
                getSupportActionBar().setTitle( device.getName());
                // Faz a conexão utilizando o mesmo UUID que o servidor utilizou
                BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);
                socket.connect();
                // Inicia o controlador chat
                chat = new ChatController(socket, this);
                chat.start();
                getSupportActionBar().setTitle("Conectado: " + device.getName());
                findViewById(R.id.btEnviarMsg).setEnabled(true);
            }
        } catch (IOException e) {
            error("Erro ao conectar: " + e.getMessage(), e);
        }
    }

    public void onClickEnviarMsg(View view) {
        String msg = tMsg.getText().toString();
        try {
            chat.sendMessage(msg);
            tMsg.setText("");
            // Mostra o texto enviado na área do chat
            String s = tMsgRecebidas.getText().toString();
            tMsgRecebidas.setText(s + "\n>> " + msg);
        } catch (IOException e) {
            error("Erro ao escrever: " + e.getMessage(), e);
        }
    }

    private void error(final String msg, final IOException e) {
        Log.e(TAG, "Erro no client: " + e.getMessage(), e);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMessageReceived(final String msg) {
        Log.d(TAG,"onMessageReceived: " + msg);
        // É chamado numa thread, portanto use o runOnUiThread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String s = tMsgRecebidas.getText().toString();
                tMsgRecebidas.setText(s + "\n<< " + msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(chat != null) {
            chat.stop();
        }
    }

}

