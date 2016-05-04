package br.com.livroandroid.hellomaterial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ExemploPaletteActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_palette);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);

        findViewById(R.id.btOk).setOnClickListener(onClick());
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img = (ImageView) findViewById(R.id.img);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.planeta_03_terra);

                Log.d("livroandroid", ">> Palette.generate");
                Palette p = Palette.generate(bitmap);

                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {

                    }
                });

                Log.d("livroandroid", "<< Palette.generate: " + p);

                text1.setTextColor(p.getVibrantColor(getDefauColor()));
                text2.setTextColor(p.getDarkVibrantColor(getDefauColor()));
                text3.setTextColor(p.getLightVibrantColor(getDefauColor()));

                text4.setTextColor(p.getMutedColor(getDefauColor()));
                text5.setTextColor(p.getDarkMutedColor(getDefauColor()));
                text6.setTextColor(p.getLightMutedColor(getDefauColor()));
            }
        };
    }

    private int getDefauColor() {
        return getResources().getColor(R.color.verde);
    }

}
