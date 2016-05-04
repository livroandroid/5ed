package br.com.livroandroid.cap07_view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class ExemploGridViewActivity extends Activity {
    // Array com os ids das imagens
    private int[] imagens = {R.drawable.smile1, R.drawable.smile2,
            R.drawable.smile1, R.drawable.smile2, R.drawable.smile1,
            R.drawable.smile2, R.drawable.smile1, R.drawable.smile2,
            R.drawable.smile1, R.drawable.smile2, R.drawable.smile1,
            R.drawable.smile2, R.drawable.smile1, R.drawable.smile2};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_exemplo_gridview);
        GridView grid = (GridView) findViewById(R.id.grid1);
        grid.setOnItemClickListener(onGridViewItemClick());
        // Informar o adapter para preencher o GridView
        grid.setAdapter(new ImagemAdapter(this, imagens));
    }

    // Evento ao clicar no item do grid
    private OnItemClickListener onGridViewItemClick() {
        return new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int posicao, long id) {
                Toast.makeText(ExemploGridViewActivity.this, "Imagem selecionada: " +
                        posicao, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
