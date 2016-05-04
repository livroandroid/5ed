package br.com.livroandroid.hellofragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Ricardo Lecheta on 23/12/2014.
 */
public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        // O fragment é livre para ter qualquer lógica aqui
        return view;
    }

    public void hello() {
        Toast.makeText(getActivity(),"Hello Frag1",Toast.LENGTH_LONG).show();
    }
}
