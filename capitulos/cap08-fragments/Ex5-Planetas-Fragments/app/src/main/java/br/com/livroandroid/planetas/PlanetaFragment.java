package br.com.livroandroid.planetas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlanetaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_planeta, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null) {
            String planeta = getArguments().getString("planeta");
            setPlaneta(planeta);
        }
    }
    public void setPlaneta(String planeta) {
        TextView text = (TextView) getView().findViewById(R.id.text);
        text.setText("Fragment Planeta: " + planeta);
    }
}
