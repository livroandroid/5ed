package br.com.livroandroid.demofragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment4 extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_4, container, false);

        if (getArguments() != null) {
            String nome = getArguments().getString("nome");

            TextView text = (TextView) view.findViewById(R.id.text);
            text.setText("Olá " + nome);
        }

        return view;
    }
}
