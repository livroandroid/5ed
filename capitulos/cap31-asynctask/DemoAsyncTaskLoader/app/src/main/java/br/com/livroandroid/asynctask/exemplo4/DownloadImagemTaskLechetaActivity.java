package br.com.livroandroid.asynctask.exemplo4;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.livroandroid.asynctask.R;
import br.com.livroandroid.asynctask.exemplo3.DownloadImagemLoaderFragment;

public class DownloadImagemTaskLechetaActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_imagem_task_lecheta);

        TextView t = (TextView) findViewById(R.id.text);
        t.setText(R.string.texto_exemplo4);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.layoutFrag, new DownloadImagemTaskLechetaFragment()).commit();
        }
    }
}
