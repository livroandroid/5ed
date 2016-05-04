package br.com.livroandroid.asynctask.exemplo2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import br.com.livroandroid.asynctask.R;

/**
 * Exemplo de download de imagem com Fragment
 *
 * Se girar a tela o fragment mantém a imagem salva.
 */
public class DownloadImagemFragmentActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_download_imagem_fragment);

        TextView t = (TextView) findViewById(R.id.text);
        t.setText(R.string.texto_exemplo2);

        if(icicle == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.layoutFrag, new DownloadImagemFragment()).commit();
        }

    }
}
