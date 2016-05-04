package br.com.livroandroid.hellotoolbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * 1) Esta activity usa a Action Bar
 *
 * 2) Foi adicionado a Toolbar no layout
 *
 * 3) Foi demonstrado a API da Toolbar
 *
 * 4) Resultado: Ambas Action Bar e Toolbar estao no layout.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_up);

        // Trata o evento de clique na Toolbar
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    Toast.makeText(getContext(), "SETTINGS", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.action_go) {
                    Toast.makeText(getContext(), "GO", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        // Up Navigation
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Infla os itens de menu na toolbar
        toolbar.inflateMenu(R.menu.menu_main);
    }

    private Context getContext() {
        return this;
    }

}
