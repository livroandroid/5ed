package br.com.livroandroid.hellomaterial;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ExemploCoordinatorLayoutActivity extends AppCompatActivity {

    private View rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_coordinator_layout);

        rootLayout = findViewById(R.id.rootLayout);
    }

    public void onClickFab(View view) {
        Snackbar
                .make(rootLayout, "Mensagem da Snackbar.", Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "OK!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
