package br.com.livroandroid.demofragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment2 extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container, false);

        return view;
    }

    public void hello() {
        Toast.makeText(getActivity(), "Hello Frag 2", Toast.LENGTH_SHORT).show();
    }
}
