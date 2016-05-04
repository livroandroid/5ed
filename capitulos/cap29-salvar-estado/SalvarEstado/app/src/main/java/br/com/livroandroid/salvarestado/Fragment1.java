package br.com.livroandroid.salvarestado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends DebugFragment {

    private int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mantém o fragment vivo
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        final TextView t = (TextView) view.findViewById(R.id.text);

        // O atributo count vai permanecer em memória
        t.setText("Count: " + count);

        view.findViewById(R.id.btOk).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                t.setText("Count: " + count);
            }
        });

        return view;
    }
}
