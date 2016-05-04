package br.com.livroandroid.multimidia.video;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import br.com.livroandroid.multimidia.R;


public class DemoVideoViewRawActivity extends ActionBarActivity {

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
        Uri uri = Uri.parse("android:resource://" + getPackageName() + "raw/last_mohicans.3gp");
        videoView.setVideoURI(uri);
        videoView.setMediaController(new MediaController(this));
        videoView.start();
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
