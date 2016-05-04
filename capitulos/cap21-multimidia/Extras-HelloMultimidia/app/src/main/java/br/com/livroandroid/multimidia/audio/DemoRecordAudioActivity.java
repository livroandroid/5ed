package br.com.livroandroid.multimidia.audio;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.File;

import br.com.livroandroid.multimidia.R;
import livroandroid.lib.utils.IntentUtils;
import livroandroid.lib.utils.SDCardUtils;

/**
 * Mostra como gravar vídeo
 *
 * @author ricardo
 */
public class DemoRecordAudioActivity extends ActionBarActivity {
    // Caminho para salvar o arquivo
    private File file;
    private MediaPlayer player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_camera_video);

        findViewById(R.id.btAbrirCamera).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria o caminho do arquivo no sdcard
                file = SDCardUtils.getPublicFile("audio.mp3", Environment.DIRECTORY_MUSIC);
                // Chama a intent informando o arquivo para salvar a foto
                Intent i = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                if (IntentUtils.isAvailable(getBaseContext(), i)) {
                    startActivityForResult(i, 0);
                } else {
                    Toast.makeText(getBaseContext(), "No Audio Recorded app.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null) {
            // Se girou a tela recupera o estado
            file = (File) savedInstanceState.getSerializable("file");
            playAudio(file);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar o estado caso gire a tela
        outState.putSerializable("file", file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && file != null) {
            playAudio(file);
        }
    }

    // Atualiza a imagem na tela
    private void playAudio(File file) {
        if (file != null && file.exists()) {
            Log.d("foto", file.getAbsolutePath());

            // Mostra o vídeo
            String path = file.getAbsolutePath();
            player = MediaPlayer.create(this, Uri.fromFile(file));
            player.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        player.stop();
    }
}