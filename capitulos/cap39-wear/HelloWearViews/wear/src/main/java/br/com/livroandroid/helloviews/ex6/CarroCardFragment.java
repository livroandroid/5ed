package br.com.livroandroid.helloviews.ex6;

import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.livroandroid.helloviews.R;

/**
 * Created by ricardo on 19/04/15.
 */
public class CarroCardFragment extends CardFragment {
    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro_card, container, false);
        // title
        TextView tNome = (TextView) view.findViewById(R.id.tNome);
        tNome.setText(getArguments().getString("nome"));
        // foto
        ImageView img = (ImageView) view.findViewById(R.id.img);
        img.setImageResource(getArguments().getInt("img"));
        return view;
    }
}