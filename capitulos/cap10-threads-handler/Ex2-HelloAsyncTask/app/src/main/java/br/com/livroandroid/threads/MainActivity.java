package br.com.livroandroid.threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * Faz download de uma imagem com a classe AsyncTask
 *
 * @author rlecheta
 */
public class MainActivity extends AppCompatActivity {
    private static final String URL = "http://livroandroid.com.br/imgs/livro_android.png";
    private ProgressBar progress;
    private ImageView imgView;
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.img);
        progress = (ProgressBar) findViewById(R.id.progress);
        // Faz o download
        downloadImagem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            downloadImagem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Faz o download da imagem em uma nova thread
    private void downloadImagem() {
        // Cria uma AsyncTask
        DownloadTask task = new DownloadTask();
        // Executa a task/thread
        task.execute();
    }

    private class DownloadTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Mostra o ProgressBar para fazer a animacao
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            // Faz o download da imagem
            try {
                bitmap = Download.downloadBitmap(URL);
            } catch (Exception e) {
                Log.e("livroandroid", e.getMessage(), e);
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                // Esconde o progress
                progress.setVisibility(View.INVISIBLE);
                // Atualiza a imagem
                imgView.setImageBitmap(bitmap);
            }
        }
    }

    public Context getContext() {
        return this;
    }
}
