package br.com.livroandroid.multimidia.audio;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import br.com.livroandroid.multimidia.R;
import livroandroid.lib.utils.SDCardUtils;


public class DemoMediaPlayerSdCardActivity extends ActionBarActivity {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_media_player);
    }

    public void onClickStart(View view) {
        if (player == null) {
            // IMPORTANTE: O arquivo precisa estar no sdcard

            File file = SDCardUtils.getPublicFile("linkin_park1.mp3", Environment.DIRECTORY_MUSIC);
            Log.d("livroandroid", file.getAbsolutePath());

            if (file.exists()) {
                player = MediaPlayer.create(this, Uri.fromFile(file));
                player.start();
            } else {
                Toast.makeText(this, "Arquivo não encontrado: " + file, Toast.LENGTH_SHORT).show();
            }
        } else {
            player.start();
        }
    }

    public void onClickPause(View view) {
        if (player != null) {
            player.pause();
        }
    }

    public void onClickStop(View view) {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }
}
