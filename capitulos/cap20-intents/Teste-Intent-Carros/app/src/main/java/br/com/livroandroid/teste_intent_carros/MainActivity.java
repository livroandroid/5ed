package br.com.livroandroid.teste_intent_carros;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMostrarFerrariFF(View view) {
        Uri uri = Uri.parse("carros://br.com.livroandroid.carros/carros/Ferrari FF");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickSelecionarCarro(View view) {
        Uri uri = Uri.parse("carros://br.com.livroandroid.carros/carros");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 99) {
            // Le os dados do carro selecionado
            String nome = data.getStringExtra("nome");
            String url_foto = data.getStringExtra("url_foto");
            Log.d("livroandroid", "Foto: " + url_foto);
            // Mostra os dados do carro selecionado
            EditText text = (EditText) findViewById(R.id.tNomeCarro);
            ImageView img = (ImageView) findViewById(R.id.imgFotoCarro);
            text.setText(nome);
            Picasso.with(this).load(url_foto).into(img);
        }
    }
}