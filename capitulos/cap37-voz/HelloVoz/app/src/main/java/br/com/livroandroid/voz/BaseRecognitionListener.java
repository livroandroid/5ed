package br.com.livroandroid.voz;

import android.os.Bundle;
import android.speech.RecognitionListener;

/**
 * Created by Usuário on 13/04/2015.
 */
public class BaseRecognitionListener implements RecognitionListener {
    // Indica que o usuário começou a falar
    public void onBeginningOfSpeech() { }
    // Buffer que vai sendo montado a medida que o usuário vai falando.
    // Este método não é garantido que seja chamado.
    public void onBufferReceived(byte[] buffer) { }
    // Indica que o usuário terminou de falar
    public void onEndOfSpeech() { }
    // Indica que ocorreu um erro no reconhecimento de voz
    public void onError(int error) { }
    // No momento este método não é utilizado.
    public void onEvent(int eventType, Bundle params) { }
    public void onPartialResults(Bundle partialResults) { }
    // Chamado quando a aplicação está pronta para receber o comando de voz
    public void onReadyForSpeech(Bundle params) { }
    // Chamado para entregar os resultados para a aplicação
    public void onResults(Bundle results) { }
    // Inidica que o nível de som do áudio mudou.
    public void onRmsChanged(float rmsdB) {	}
}

