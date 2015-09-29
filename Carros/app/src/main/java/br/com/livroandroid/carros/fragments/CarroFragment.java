package br.com.livroandroid.carros.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarroFragment extends BaseFragment {
    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
        carro = Parcels.unwrap(getArguments().getParcelable("carro"));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Atualiza a view do fragment com os dados do carro
        setTextString(R.id.tDesc, carro.desc);
        final ImageView imgView = (ImageView) getView().findViewById(R.id.img);
        Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);
    }
}

