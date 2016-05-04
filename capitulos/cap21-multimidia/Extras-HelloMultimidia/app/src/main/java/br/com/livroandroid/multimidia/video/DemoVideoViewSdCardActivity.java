package br.com.livroandroid.multimidia.video;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import br.com.livroandroid.multimidia.R;
import livroandroid.lib.utils.SDCardUtils;


public class DemoVideoViewSdCardActivity extends ActionBarActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo_video_view);

        // O VideoView é a única view desta tela
        videoView = (VideoView) findViewById(R.id.videoView);

        loadVideo();
    }

    private void loadVideo() {
        // IMPORTANTE: O arquivo precisa estar no sdcard
        File file = SDCardUtils.getPublicFile("last_mohicans.3gp", Environment.DIRECTORY_MOVIES);

        // Copie pro Emulador: /storage/sdcard/Movies/last_mohicans.3gp
        Log.d("livroandroid", "play: " + file.getAbsolutePath());

        if (file.exists()) {
            String path = file.getAbsolutePath();
            videoView.setVideoPath(path);
            videoView.setMediaController(new MediaController(this));
            videoView.start();

            Log.d("livroandroid", "start : " + file.getAbsolutePath());
        } else {
            Toast.makeText(this, "Arquivo não encontrado: " + file, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickStart(View view) {
        videoView.start();
    }

    public void onClickPause(View view) {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    public void onClickStop(View view) {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }
}
