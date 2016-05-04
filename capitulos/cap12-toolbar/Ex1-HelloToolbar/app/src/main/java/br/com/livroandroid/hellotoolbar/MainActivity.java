package br.com.livroandroid.hellotoolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 1) Esta activity desligou a action bar padrao pelo Tema
 *
 * android:theme="@style/AppTheme.NoActionBar"
 *
 * 2) Foi adicionado a Toolbar no layout
 *
 * 3) A toolbar eh setada como a ActionBar
 *
 * setSupportActionBar(toolbar);
 *
 * 4) De resto pode usar os metodos da action bar normalmente.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Aqui eh a magica (A toolbar sera a action bar).
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_go) {
            Toast.makeText(this, "GO", Toast.LENGTH_SHORT).show();
        } else  if (id == R.id.action_settings) {
            Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
