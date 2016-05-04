package br.com.livroandroid.property_animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class ExemploViewPropertyAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_animacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAnimarXML(View view) {
        Toast.makeText(this, "Somente pela API", Toast.LENGTH_SHORT).show();
    }

    public void onClickAnimarAPI(View view) {
        ImageView img = (ImageView) findViewById(R.id.img);
        img.animate().x(200).y(200).alpha(0).setDuration(1000).start();

        Toast.makeText(this, "img.animate().x(400).y(400).alpha(0)", Toast.LENGTH_SHORT).show();
    }
}
