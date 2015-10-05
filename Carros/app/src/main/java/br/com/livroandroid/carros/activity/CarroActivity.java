package br.com.livroandroid.carros.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.fragments.CarroFragment;

public class CarroActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);
        // Configura a Toolbar como a action bar
        setUpToolbar();
        // Título da toolbar e botão up navigation
        Carro c = Parcels.unwrap(getIntent().getParcelableExtra("carro"));
        getSupportActionBar().setTitle(c.nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Imagem de header na action bar
        ImageView appBarImg = (ImageView) findViewById(R.id.appBarImg);
        Picasso.with(getContext()).load(c.urlFoto).into(appBarImg);
        // Adiciona o fragment no layout
        if (savedInstanceState == null) {
            // Cria o fragment com o mesmo Bundle (args) da intent
            CarroFragment frag = new CarroFragment();
            frag.setArguments(getIntent().getExtras());
            // Adiciona o fragment no layout
            getSupportFragmentManager().beginTransaction().add(R.id.CarroFragment, frag).commit();
        }
    }

    public void setTitle(String s) {
        // O título deve ser setado na CollapsingToolbarLayout
        CollapsingToolbarLayout c = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        c.setTitle(s);
    }
}
