package br.com.livroandroid.salvarestado;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


public class ProgressBarDemoFragment extends Fragment {

    private int progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_bar_demo, container, false);

        Button b = (Button) view.findViewById(R.id.btOK);
        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                executarTarefa();
            }
        });

        return view;
    }

    private void executarTarefa() {
        new Thread(new Runnable() {
            public void run() {
                for (int i = progress; i <= 100; i++) {
                    progress = i;
                    updateProgressBar();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
                progress = 0;
                Log.d("livroandroid", "Fim");
            }
        }).start();
    }

    private void updateProgressBar() {
        // Atualiza a barra de progresso
        if (getView() != null && !isDetached()) {
            getView().post(new Runnable() {
                public void run() {
                    // Barra de Progresso
                    final ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.barraProgresso);

                    Log.d("livroandroid", ">> Progress: " + progress);
                    progressBar.setProgress(progress);
                }
            });
        }
    }
}
