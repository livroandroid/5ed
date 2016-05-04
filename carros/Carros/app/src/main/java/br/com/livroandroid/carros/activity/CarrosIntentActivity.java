package br.com.livroandroid.carros.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.CarroAdapter;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroDB;

public class CarrosIntentActivity extends BaseActivity {
    private List<Carro> carros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carros_intent);
        // (*1*) Lê as informações da intent
        Intent intent = getIntent();
        Uri uri = intent.getData();
        Log.d("livroandroid", "Action: " + intent.getAction());
        Log.d("livroandroid", "Scheme: " + uri.getScheme());
        Log.d("livroandroid", "Host: " + uri.getHost());
        Log.d("livroandroid", "Path: " + uri.getPath());
        Log.d("livroandroid", "PathSegments: " + uri.getPathSegments());
        // RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // Configura a Toolbar como a action bar
        setUpToolbar();
        CarroDB db = new CarroDB(this);
        try {
            if ("/carros".equals(uri.getPath())) {
                // (*2*)Lista todos os carros
                this.carros = db.findAll();
                recyclerView.setAdapter(new CarroAdapter(this, carros, onClickCarro()));
            } else {
                // (*3*) Busca o carro pelo nome: /carros/Ferrari FF
                List<String> paths = uri.getPathSegments();
                String nome = paths.get(1);
                Carro carro = db.findByNome(nome);
                if (carro != null) {
                    // Se encontrou o carro, abre a activity para mostrá-lo
                    Intent carroIntent = new Intent(this, CarroActivity.class);
                    carroIntent.putExtra("carro", carro);
                    startActivity(carroIntent);
                    finish();
                }
            }
        } finally {
            db.close();
        }
    }

    private CarroAdapter.CarroOnClickListener onClickCarro() {
        return new CarroAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro(View view, int idx) {
                Carro c = carros.get(idx);
                // (*4*) Retorna o carro selecionado para quem chamou
                Intent intent = new Intent();
                intent.putExtra("nome", c.nome);
                intent.putExtra("url_foto", c.urlFoto);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

            @Override
            public void onLongClickCarro(View view, int idx) {
                // nada aqui
            }
        };
    }
}
