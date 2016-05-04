package br.com.livroandroid.cap07_view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ExemploToggleButtonActivity extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_exemplo_toogle_button);
        final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle);
        Button b = (Button) findViewById(R.id.btOK);
        final Context context = this;
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selecionado = toggle.isChecked();
                Toast.makeText(context, "Selecionado: " + selecionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
