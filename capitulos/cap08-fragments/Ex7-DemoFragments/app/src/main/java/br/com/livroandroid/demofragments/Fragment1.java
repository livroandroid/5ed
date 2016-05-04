package br.com.livroandroid.demofragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment1 extends android.support.v4.app.Fragment {

    private int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // Ou apenas faz isso.
        //setRetainInstance(true);

        // Recupera o estado da variável
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
        }

        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText("Este é o Frag 1");

        view.findViewById(R.id.btOk).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                Toast.makeText(getActivity(), "Count: " + count, Toast.LENGTH_LONG).show();
            }
        });

        view.findViewById(R.id.btHelloActivity).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity a = (MainActivity) getActivity();
                a.hello();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    public void hello() {
        Toast.makeText(getActivity(), "Hello Frag 1", Toast.LENGTH_SHORT).show();
    }
}
