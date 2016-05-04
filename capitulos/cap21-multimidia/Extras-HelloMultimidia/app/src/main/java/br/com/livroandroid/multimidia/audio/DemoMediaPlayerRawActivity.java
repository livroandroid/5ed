package br.com.livroandroid.multimidia.audio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import br.com.livroandroid.multimidia.R;


public class DemoMediaPlayerRawActivity extends ActionBarActivity {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_media_player);
    }

    public void onClickStart(View view) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.linkin_park1);
            player.start();
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
