package br.com.livroandroid.hellomaterial;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


/*
* FAB
* http://www.google.com/design/spec/components/buttons.html#buttons-flat-raised-buttons
*
* */
public class ExemploFloatingButtonActivity extends AppCompatActivity {

    private View rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_floating_button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = findViewById(R.id.rootLayout);

        // Animação ao abrir o FAB
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        findViewById(R.id.btFab1).startAnimation(animation);
        findViewById(R.id.btFab2).startAnimation(animation);
        findViewById(R.id.btFab3).startAnimation(animation);
    }

    public void onClickFab1(View view) {
        Toast.makeText(this, "FAB 1", Toast.LENGTH_SHORT).show();
    }


    public void onClickFab2(View view) {
        Snackbar
                .make(rootLayout, "Clicou no FAB.", Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "OK!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void onClickFab3(View view) {
        Snackbar
                .make(rootLayout, "Clicou no FAB mini.", Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "OK!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
