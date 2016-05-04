package br.com.livroandroid.asynctask.exemplo4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.asynctask.Download;
import br.com.livroandroid.asynctask.R;
import livroandroid.lib.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadImagemTaskLechetaFragment extends BaseFragment implements View.OnClickListener {
    private static final String URL = "http://livroandroid.com.br/imgs/livro_android.png";
    private ImageView imgView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_download_imagem_task_lecheta, container, false);

        imgView = (ImageView) view.findViewById(R.id.img);

        view.findViewById(R.id.btDownloadComProgressBar).setOnClickListener(this);
        view.findViewById(R.id.btDownloadComProgressDialog).setOnClickListener(this);
        view.findViewById(R.id.btDownloadComPicasso).setOnClickListener(this);

        setRetainInstance(true);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btDownloadComProgressBar) {
            imgView.setImageBitmap(null);
            // O R.id.progress indica o ProgressBar que deve ser animado
            startTask("1", new DownloadTask(), R.id.progress);
        } else if(v.getId() == R.id.btDownloadComProgressDialog) {
            imgView.setImageBitmap(null);
            // Se não passar o R.id.progress vai abrir um ProgressDialog
            startTask("1",new DownloadTask());
        } else if(v.getId() == R.id.btDownloadComPicasso) {
            imgView.setImageBitmap(null);
            Picasso.with(getContext()).load(URL).into(imgView);
        }
    }

    class DownloadTask extends BaseTask<Bitmap> {
        @Override
        public Bitmap execute() throws Exception {
            return Download.downloadBitmap(URL);
        }
        @Override
        public void updateView(Bitmap bitmap) {
            imgView.setImageBitmap(bitmap);
        }
    }
}
