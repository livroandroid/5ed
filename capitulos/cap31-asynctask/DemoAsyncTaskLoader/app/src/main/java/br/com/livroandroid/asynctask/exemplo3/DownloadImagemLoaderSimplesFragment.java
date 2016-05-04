package br.com.livroandroid.asynctask.exemplo3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.livroandroid.asynctask.R;

/**
 * Exemplo de download de imagem com Loader e AsyncTaskLoader
 *
 */
public class DownloadImagemLoaderSimplesFragment extends Fragment {
    private ProgressDialog progress;
    private ImageView imgView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download_imagem, container, false);

        imgView = (ImageView) view.findViewById(R.id.img);

        Log.d("livroandroid","frag onCreateView");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("livroandroid","frag onActivityCreated");

        progress();

        // Inicializa o Loader ou reconecta em um existente
        Log.d("livroandroid", "initLoader()");
        getLoaderManager().initLoader(0, null, new LoaderCallbacks());
    }

    private void progress() {

        imgView.setImageBitmap(null);

        progress = ProgressDialog.show(getContext(), "Aguarde", "Fazendo o download...");
        progress.setCancelable(true);
        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d("livroandroid", "onCancel()");
                // Cancela o loader
                getLoaderManager().getLoader(0).reset();
            }
        });
    }

    private class LoaderCallbacks implements LoaderManager.LoaderCallbacks<Bitmap> {

        @Override
        public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
            Log.d("livroandroid", "onCreateLoader");
            return new ImagemLoader(getContext());
        }

        @Override
        public void onLoadFinished(Loader<Bitmap> loader, Bitmap data) {
            Log.d("livroandroid", "onLoadFinished");
            setBitmap(data);
        }

        @Override
        public void onLoaderReset(Loader<Bitmap> loader) {
             Log.d("livroandroid", "onLoaderReset");
        }
    }

    private void setBitmap(Bitmap imagem){

        Log.d("livroandroid","setBitmap");

        // Esconde o progress
        closeProgress();

        // Atualiza a imagem
        imgView.setImageBitmap(imagem);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("livroandroid", "frag onDetach");
        closeProgress();
    }

    public Context getContext() {
        return getActivity();
    }

    private void closeProgress() {
        if(progress != null && progress.isShowing()) {
            progress.dismiss();
            progress = null;
        }
    }
}
