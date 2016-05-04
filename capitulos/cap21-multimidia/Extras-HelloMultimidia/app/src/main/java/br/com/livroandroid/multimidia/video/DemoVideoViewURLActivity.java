package br.com.livroandroid.multimidia.video;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import br.com.livroandroid.multimidia.R;


public class DemoVideoViewURLActivity extends ActionBarActivity {

    private VideoView videoView;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo_video_view);

        // O VideoView é a única view desta tela
        videoView = (VideoView) findViewById(R.id.videoView);
    }

    public void onClickStart(View view) {
        String url = "http://www.livroiphone.com.br/carros/esportivos/ferrari_ff.mp4";

        if (videoView.getCurrentPosition() == 0) {
            videoView.setVideoURI(Uri.parse(url));
            videoView.setMediaController(new MediaController(this));
            videoView.start();
        } else {
            videoView.seekTo(position);
            videoView.start();
        }
    }

    public void onClickPause(View view) {
        if (videoView.isPlaying()) {
            position = videoView.getCurrentPosition();
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
