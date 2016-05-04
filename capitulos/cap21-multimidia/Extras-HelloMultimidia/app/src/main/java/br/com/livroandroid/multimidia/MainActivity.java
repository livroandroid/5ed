package br.com.livroandroid.multimidia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.multimidia.audio.DemoMediaPlayerRawActivity;
import br.com.livroandroid.multimidia.audio.DemoMediaPlayerSdCardActivity;
import br.com.livroandroid.multimidia.audio.DemoRecordAudioActivity;
import br.com.livroandroid.multimidia.camera.DemoCameraArquivoActivity;
import br.com.livroandroid.multimidia.camera.DemoCameraThumbActivity;
import br.com.livroandroid.multimidia.video.DemoRecordVideoActivity;
import br.com.livroandroid.multimidia.video.DemoVideoViewRawActivity;
import br.com.livroandroid.multimidia.video.DemoVideoViewSdCardActivity;
import br.com.livroandroid.multimidia.video.DemoVideoViewURLActivity;
import livroandroid.lib.utils.IntentUtils;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);

        String[] items = new String[]{
                "Vídeo - Intent player nativo",
                "Vídeo - VideoView /res/raw",
                "Vídeo - VideoView sdcard",
                "Vídeo - VideoView URL",
                "Camera - Thumb",
                "Camera - Arquivo",
                "Camera - Gravar Vídeo",
                "Áudio - /res/raw",
                "Áudio - sdcard",
                "Áudio - Gravar",

        };

        ListView listView = new ListView(this);
        setContentView(listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    IntentUtils.showVideo(this, "http://www.livroiphone.com.br/carros/esportivos/ferrari_ff.mp4");
                    break;
                case 1:
                    startActivity(new Intent(this, DemoVideoViewRawActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(this, DemoVideoViewSdCardActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(this, DemoVideoViewURLActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(this, DemoCameraThumbActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(this, DemoCameraArquivoActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(this, DemoRecordVideoActivity.class));
                    break;
                case 7:
                    startActivity(new Intent(this, DemoMediaPlayerRawActivity.class));
                    break;
                case 8:
                    startActivity(new Intent(this, DemoMediaPlayerSdCardActivity.class));
                    break;
                case 9:
                    startActivity(new Intent(this, DemoRecordAudioActivity.class));
                    break;

                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}