package br.com.livroandroid.asynctask.exemplo3;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import br.com.livroandroid.asynctask.Download;

/**
 * Created by Ricardo Lecheta on 28/09/2014.
 */
public class ImagemLoader extends AsyncTaskLoader<Bitmap> {

    private static final String URL = "http://livroandroid.com.br/imgs/livro_android.png";
    private Bitmap bitmap;

    public ImagemLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        // Chamado para iniciar o loader ou reconectar em um já existente
        // Precisa chamar o forceLoad() para executar o loader "loadInBackground"
        if(bitmap == null) {
            Log.d("livroandroid", "loader onStartLoading() >> forceLoad()");
            // Executa o loader
            forceLoad();
        } else {
            Log.d("livroandroid", "loader onStartLoading() >> deliverResult()");
            // Já possui os dados, apenas atualiza a interface
            // Isso vai chamar o método onLoadFinished() na interface de callback
            deliverResult(bitmap);
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        // Chamado ao executar o método forceLoad()
        Log.d("livroandroid", "loader onForceLoad()");
    }

    @Override
    public Bitmap loadInBackground() {
        Log.d("livroandroid", "loader loadInBackground()");
        // Faz o download da imagem
        try {
            this.bitmap = Download.downloadBitmap(URL);
        } catch (Exception e) {
            Log.e("livroandroid",e.getMessage(), e);
        }
        return this.bitmap;
    }

    @Override
    public void deliverResult(Bitmap data) {
        // Executa depois do loadInBackground(). Chamado antes de enviar o resultado para a UI Thread
        Log.d("livroandroid", "loader deliverResult, isStarted(): " + isStarted());
        if(isStarted() && !isReset()) {
            Log.d("livroandroid", "loader super.deliverResult(): " + bitmap);
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStopLoading() {
        // Chamado quando o loader será parado
        // Acontece quando o método onStop() é chamado na activity ou fragment
        super.onStopLoading();
        Log.d("livroandroid", "loader onStopLoading()");
    }

    @Override
    protected void onReset() {
        super.onReset();
        // Chamado caso o loader tenha sido cancelado pelo método reset()
        Log.d("livroandroid", "loader onReset()");
        this.bitmap = null;
    }

    @Override
    public void onCanceled(Bitmap data) {
        super.onCanceled(data);
        Log.d("livroandroid", "loader onCanceled()");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        // Chamado se a fonte de dados utilizada pelo loader foi alterada, neste caso a interface deve ser alterada para refletir os novos dados.
        // Não é utilizado muito com web services, estude o CursorLoader para entender isso.
        Log.d("livroandroid", "loader onContentChanged()");
    }
}