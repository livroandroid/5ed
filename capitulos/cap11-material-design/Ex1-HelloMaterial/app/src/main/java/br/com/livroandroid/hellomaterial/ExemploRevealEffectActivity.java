package br.com.livroandroid.hellomaterial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class ExemploRevealEffectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_reveal_effect);

        findViewById(R.id.btShow).setOnClickListener(onClickShow());
        findViewById(R.id.btHide).setOnClickListener(onClickHide());
    }

    private View.OnClickListener onClickShow() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View img = findViewById(R.id.img);
                RevealEffect.show(img, 500);
            }
        };
    }

    private View.OnClickListener onClickHide() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View img = findViewById(R.id.img);
                RevealEffect.hide(img, 500);
            }
        };
    }
}
