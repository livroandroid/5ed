package br.com.livroandroid.multimidia.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

import br.com.livroandroid.multimidia.R;
import livroandroid.lib.utils.SDCardUtils;

/**
 * Mostra como gravar vídeo
 *
 * @author ricardo
 */
public class DemoRecordVideoActivity extends ActionBarActivity {
    // Caminho para salvar o arquivo
    private File file;
    private VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_camera_video);

        videoView = (VideoView) findViewById(R.id.videoView);

        ImageButton b = (ImageButton) findViewById(R.id.btAbrirCamera);
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria o caminho do arquivo no sdcard
                file = SDCardUtils.getPublicFile("video.mp4", Environment.DIRECTORY_MOVIES);
                // Chama a intent informando o arquivo para salvar a foto
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(i, 0);
            }
        });

        if (savedInstanceState != null) {
            // Se girou a tela recupera o estado
            file = (File) savedInstanceState.getSerializable("file");
            showVideo(file);
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
            showVideo(file);
        }
    }

    // Atualiza a imagem na tela
    private void showVideo(File file) {
        if (file != null && file.exists()) {
            Log.d("foto", file.getAbsolutePath());

            // Mostra o vídeo
            String path = file.getAbsolutePath();
            videoView.setVideoPath(path);
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus();
            videoView.start();
        }
    }
}