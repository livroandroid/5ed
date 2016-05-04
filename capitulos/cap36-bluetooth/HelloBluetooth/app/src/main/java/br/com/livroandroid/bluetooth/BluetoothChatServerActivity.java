package br.com.livroandroid.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

/**
 * Fica aguardando alguém se conectar
 *
 * @author Ricardo Lecheta
 */
public class BluetoothChatServerActivity extends BluetoothChatClientActivity implements ChatController.ChatListener {
    private static final UUID uuid = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    private boolean running;
    private BluetoothServerSocket serverSocket;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // Deixa o servidor disponível para busca
        BluetoothUtil.makeVisible(this,300);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Inicia a thread do chat para não travar a UI
        new ChatThread().start();
    }

    class ChatThread extends Thread {
        @Override
        public void run() {
            try {
                // Abre o socket servidor (o cliente precisa utilizar o mesmo UUID)
                serverSocket =
                        btfAdapter.listenUsingRfcommWithServiceRecord("LivroAndroid", uuid);
                Log.d(TAG, "Servidor aguardando conexão...");
                // Aguarda até alguém conectar (esta chamada é bloqueante)
                BluetoothSocket socket = serverSocket.accept();
                Log.d(TAG, "socket accept " + socket);
                if (socket != null) {
                    // Mostra o device nome do device que conectou
                    final BluetoothDevice device = socket.getRemoteDevice();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getSupportActionBar().setTitle("Conectado: " + device.getName());
                            findViewById(R.id.btEnviarMsg).setEnabled(true);
                            Toast.makeText(getBaseContext(),"Conectou: " + device.getName(),Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Alguém conectou
                    chat = new ChatController(socket, BluetoothChatServerActivity.this);
                    chat.start();

                }

            } catch (IOException e) {
                Log.e(TAG, "Erro no servidor: " + e.getMessage(), e);
                running = false;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
        }
    }
}
