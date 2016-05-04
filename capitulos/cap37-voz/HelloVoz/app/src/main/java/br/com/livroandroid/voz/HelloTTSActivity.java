package br.com.livroandroid.voz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import livroandroid.lib.activity.BaseActivity;
import livroandroid.lib.utils.SDCardUtils;


/**
 * Mostra como fazer o Android falar com TTS Text-To-Speech.
 */
public class HelloTTSActivity extends BaseActivity implements TextToSpeech.OnInitListener {

    private static final String TAG = "livroandroid";
    private static final int ACTION_CHECK_DATA_CODE = 1;
    private TextView tMsg;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_tts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tMsg = (TextView) findViewById(R.id.tMsg);
        tts = new TextToSpeech(this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hello_tts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_pt_br) {
            Locale locale = new Locale("pt","BR");
            tts.setLanguage(locale);
            return true;
        } else if(id == R.id.action_en_us) {
            Locale locale = Locale.ENGLISH;
            tts.setLanguage(locale);
            return true;
        }else if (id == R.id.action_check_data) {
            // Verifica se o pacote de dados do TTS está instalado
            Intent checkIntent = new Intent();
            checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            startActivityForResult(checkIntent, ACTION_CHECK_DATA_CODE);
            return true;
        } else if(id == R.id.action_install_data) {
            // Instala o pacote de dados
            Intent installIntent = new Intent();
            installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {
        Log.d(TAG,"Engine TTS inicializada com sucesso: " + Locale.getDefault());
        // Deixa inglês por padrão
        tts.setLanguage(Locale.getDefault());
    }

    public void onClickFalarTexto(View view) {
        String s = tMsg.getText().toString();
        tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
        Log.d(TAG,"Speak: " + s);
    }

    public void onClickSalvar(View view) {
        String s = tMsg.getText().toString();
        // Sintetiza a voz para arquivo
        File file = SDCardUtils.getPublicFile("arquivo-voz.wav", Environment.DIRECTORY_MUSIC);
        HashMap<String, String> params = new HashMap<String,String>();
        tts.synthesizeToFile(s, params, file.getAbsolutePath());
        toast("Voz salva em arquivo: " + file);
    }

    public void onClickFalarArquivo(View view) {
        File file = SDCardUtils.getPublicFile("arquivo-voz.wav", Environment.DIRECTORY_MUSIC);
        if(file.exists()) {
            try {
                MediaPlayer mp = new MediaPlayer();
                mp.setDataSource(file.getAbsolutePath());
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                Log.e(TAG,"Erro ao tocar: " + e.getMessage(), e);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_CHECK_DATA_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                toast("Pacote de dados de voz OK!");
            } else {
                // Falta pacote, solicita instalação
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera os recursos da engine do TTS
        tts.shutdown();
    }
}
