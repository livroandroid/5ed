package br.com.livroandroid.contatos.exemplo3;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.contatos.R;
import br.com.livroandroid.contatos.adapter.ContatoCursorAdapter;
import br.com.livroandroid.contatos.agenda.Agenda;
import br.com.livroandroid.contatos.agenda.Contato;

/**
 * Mostra como utilizar o SimpleCursorAdapter diretamente
 */
public class ListaContatosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        // Lista os contatos
        Agenda a = new Agenda(this);
        Cursor cursor = a.getCursorContatos();
        final ContatoCursorAdapter adapter = new ContatoCursorAdapter(this,cursor);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Agenda a = new Agenda(this);
        Contato c = a.getContatoById(id);
        Toast.makeText(this, "Ex3: " + c.nome, Toast.LENGTH_SHORT).show();
        c.show(this);
    }
}
