package br.com.livroandroid.asynctask.exemplo3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import br.com.livroandroid.asynctask.R;

/**
 * Exemplo de download de imagem com Loader e AsyncTaskLoader
 *
 * Usa um fragment com retain.
 *
 */
public class DownloadImagemLoaderActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_download_imagem_fragment);

        TextView t = (TextView) findViewById(R.id.text);
        t.setText(R.string.texto_exemplo3);

        if(icicle == null) {
            //getSupportFragmentManager().beginTransaction().add(R.id.layoutFrag, new DownloadImagemLoaderSimplesFragment()).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.layoutFrag, new DownloadImagemLoaderFragment()).commit();
        }
    }
}
