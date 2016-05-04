package br.com.livroandroid.asynctask.exemplo2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.livroandroid.asynctask.Download;
import br.com.livroandroid.asynctask.R;

/**
 * Exemplo de download de imagem com AsyncTask
 *
 * Mostra como salvar o estado ao girar a tela, pois o frag fica vivo
 *
 * Lembre que o ProgressDialog precisa ser fechado antes de a activity ser destruída. Ver onDetach().
 *
 * Na prática recomendo usar o ProgressBar com a animação. Mostro o ProgressDialog aqui, apenas por ser mais difícil.
 *
 */
public class DownloadImagemFragment extends Fragment {
    private static final String URL = "http://livroandroid.com.br/imgs/livro_android.png";
    private ProgressDialog progress;
    private Bitmap bitmap;
    private ImageView imgView;
    private DownloadTask task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download_imagem, container, false);

        imgView = (ImageView) view.findViewById(R.id.img);

        Log.d("livroandroid","frag onCreateView()");

        if(bitmap == null) {
            // Faz o download
            downloadImagem(URL);
        } else {
            // Atualiza a imagem se recuperou o estado
            setBitmap(bitmap);
        }

        return view;
    }
    // Faz o download da imagem em uma nova Thread
    private void downloadImagem(final String urlImg) {
        // O atributo task fica retido em caso de rotação, assim podemos saber se a task já está executando
        if(task == null) {
            Log.d("livroandroid","> DownloadTask.execute()");
            // Cria uma AsyncTask
            task = new DownloadTask();
            // Executa a task/thread
            task.execute(URL);
            //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,URL);
        } else {
            // Mostra novamente o dialog para acompanhar o download
            Log.d("livroandroid","DownloadTask já está executando.");
            showProgress();
        }
    }

    private class DownloadTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPreExecute() {
            Log.d("livroandroid","task onPreExecute()");
            showProgress();
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            Log.d("livroandroid","task doInBackground()");
            // Faz o download da imagem
            Bitmap bitmap = null;
            try {
                bitmap = Download.downloadBitmap(URL);
            } catch (Exception e) {
                Log.e("livroandroid",e.getMessage(), e);
            }
            return bitmap;
        }

        @Override
        protected void onCancelled() {
            task = null;
            super.onCancelled();
            Log.d("livroandroid","task onCancelled()");
        }

        protected void onPostExecute(Bitmap imagem) {
            task = null;
            Log.d("livroandroid","task onPostExecute()");
            if(imagem != null) {
                setBitmap(imagem);
            }
        }
    }

    private void setBitmap(Bitmap imagem){
        Log.d("livroandroid","setBitmap()");
        this.bitmap = imagem;

        // Fecha o progress
        closeProgress();

        // Atualiza a imagem
        imgView.setImageBitmap(imagem);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("livroandroid","frag onDetach()");
        // Fecha o progress antes de desassociar o fragment da activity
        closeProgress();
    }

    private void showProgress() {
        Log.d("livroandroid","showProgress()");
        // Mostra o dialog e permite cancelar
        progress = ProgressDialog.show(getActivity(),"Aguarde","Fazendo o download...");
        progress.setCancelable(true);
        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // Cancela a AsyncTask
                task.cancel(true);
            }
        });
    }

    private void closeProgress() {
        Log.d("livroandroid","closeProgress()");
        if(progress != null && progress.isShowing()) {
            progress.dismiss();
            progress = null;
        }
    }
}
