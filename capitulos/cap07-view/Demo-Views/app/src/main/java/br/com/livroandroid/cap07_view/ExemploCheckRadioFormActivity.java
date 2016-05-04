package br.com.livroandroid.cap07_view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ExemploCheckRadioFormActivity extends Activity {
    private static final String TAG = "livro";

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_exemplo_check_radio_form);
        final Context context = this;
        final EditText textNome = (EditText) findViewById(R.id.textNome);
        final RadioGroup group = (RadioGroup) findViewById(R.id.group1);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean sim = R.id.radioSim == checkedId;
                boolean nao = R.id.radioNao == checkedId;
                if (sim) {
                    Log.i(TAG, "Marcou radio Sim: " + checkedId);
                } else if (nao) {
                    Log.i(TAG, "Marcou radio Não: " + checkedId);
                }
            }
        });
        final CheckBox check = (CheckBox) findViewById(R.id.checkReceberEmail);
        // Define um listener para executar quando alterar o check
        check.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "check: " + isChecked);
            }
        });
        Button b = (Button) findViewById(R.id.buttonEnviar);
        b.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "OK");
                // Compara o id do radioSim
                boolean concorda = R.id.radioSim == group.getCheckedRadioButtonId();
                boolean receberEmail = check.isChecked();
                StringBuffer sb = new StringBuffer();
                sb.append("Nome: ").append(textNome.getText())
                        .append("\nConcorda: ").append(concorda ? "Sim" : "Não")
                        .append("\nReceber Email: ").append(receberEmail ? "Sim" : "Não");
                Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
