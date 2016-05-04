package br.com.livroandroid.playermp3;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service que fica tocando um som em background
 *
 * @author ricardo
 */
public class Mp3Service extends Service implements InterfaceMp3 {
    private static final String TAG = "livro";
    private PlayerMp3 player = new PlayerMp3();
    private String mp3;

    public class Mp3ServiceBinder extends Binder {
        // Converte para InterfaceMp3
        public InterfaceMp3 getInterface() {
            // retorna a interface para controlar o Service
            return Mp3Service.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // retorna a classe ConexaoInterfaceMp3 para a activity utilizar
        Log.d(TAG, "Mp3Service onBind(). Aqui retorna o IBinder.");
        return new Mp3ServiceBinder();
    }

    // InterfaceMp3.play(mp3)
    public void play(String mp3) {
        this.mp3 = mp3;
        try {
            player.start(mp3);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    // InterfaceMp3.pause()
    public void pause() {
        player.pause();
    }

    // InterfaceMp3.stop()
    public void stop() {
        player.stop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Mp3Service onDestroy().");
        player.close();
    }

    @Override
    public String getMp3() {
        return mp3;
    }
    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }
}
