package br.com.livroandroid.voz;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class HelloSpeechRecognizerActivity extends ActionBarActivity  {
    // Reconhecedor de voz
    private SpeechRecognizer stt;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_hello_speech_recognizer);

        listView = (ListView) findViewById(R.id.list);

        // Cria o SpeechRecognizer e configura o listener
        stt = SpeechRecognizer.createSpeechRecognizer(this);
        stt.setRecognitionListener(new BaseRecognitionListener() {
            public void onResults(Bundle results) {
                // Recupera as possíveis palavras que foram pronunciadas
                ArrayList<String> words = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                Log.d("livroandroid", "onResults: " + words);
                listView.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_list_item_1, words));
            }
        });

        // Inicia o Listener do reconhecimento de voz
        Intent intent = getRecognizerIntent();
        stt.startListening(intent);
    }

    protected Intent getRecognizerIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale algo");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera os recursos e finaliza o STT
        stt.stopListening();
        stt.destroy();
    }
}
