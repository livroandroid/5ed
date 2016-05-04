package br.com.livroandroid.cap07_view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ExemploImageButtonActivity extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_exemplo_image_button);
        ImageButton botaoImagem1 = (ImageButton) findViewById(R.id.img1);
        final Context context = this;
        botaoImagem1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Imagem 1 OK", Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton botaoImagem2 = (ImageButton) findViewById(R.id.img2);
        botaoImagem2.setImageResource(R.drawable.smile2);
        botaoImagem2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Imagem 2 OK", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
