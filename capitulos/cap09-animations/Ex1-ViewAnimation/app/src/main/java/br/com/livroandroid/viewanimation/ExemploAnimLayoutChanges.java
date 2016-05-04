package br.com.livroandroid.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Mostra a propriedade android:animateLayoutChanges="true" do layout.
 */
public class ExemploAnimLayoutChanges extends AppCompatActivity {

    private LinearLayout layout;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_anim_layout_changes);

        layout = (LinearLayout) findViewById(R.id.rootLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exemplo_anim_layout_changes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            // Adiciona uma view
            TextView t = new TextView(this);
            t.setText("Texto " + (count++));
            layout.addView(t, 0);
            return true;
        } else if (id == R.id.action_remove) {
            // Remove a última view
            int childCount = layout.getChildCount();
            if (childCount > 0) {
                layout.removeViewAt(0);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
