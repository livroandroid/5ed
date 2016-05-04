package br.com.livroandroid.helloviews.ex4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import br.com.livroandroid.helloviews.R;
import br.com.livroandroid.shared.Carro;

/**
 * Created by ricardo on 19/04/15.
 */
public class CarroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        Carro c = (Carro) getIntent().getSerializableExtra("carro");

        ImageView img = (ImageView) findViewById(R.id.img);
        img.setImageResource(c.img);
    }
}
