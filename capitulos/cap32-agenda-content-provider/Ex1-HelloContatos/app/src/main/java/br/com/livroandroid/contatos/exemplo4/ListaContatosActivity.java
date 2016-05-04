package br.com.livroandroid.contatos.exemplo4;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.contatos.R;
import br.com.livroandroid.contatos.agenda.Agenda;
import br.com.livroandroid.contatos.agenda.Contato;
import br.com.livroandroid.contatos.adapter.ContatoCursorAdapter;

/**
 * Mostra como utilizar o CursorLoader da API de Loaders
 */
public class ListaContatosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "livroandroid";
    private ListView listView;
    private CursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        // Configura o ListView com um adapter
        adapter = new ContatoCursorAdapter(this,null);
        listView.setAdapter(adapter);

        // Inicia o loader
        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Agenda a = new Agenda(this);
        Contato c = a.getContatoById(id);
        Toast.makeText(this, "Ex4: " + c.nome, Toast.LENGTH_SHORT).show();
        c.show(this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Retorna o CursorLoader para carregar os contatos
        Uri uriContatos = ContactsContract.Contacts.CONTENT_URI;
        return new CursorLoader(getApplicationContext(),uriContatos,
                null,ContactsContract.Contacts.HAS_PHONE_NUMBER +" = 1 ",null, ContactsContract.Contacts.DISPLAY_NAME);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Carrega o adapter com o cursor
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Limpa o adapter
        adapter.swapCursor(null);
    }
}
