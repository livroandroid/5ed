package br.com.livroandroid.contatos.exemplo2;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.contatos.R;
import br.com.livroandroid.contatos.agenda.Agenda;
import br.com.livroandroid.contatos.agenda.Contato;

/**
 * Mostra como utilizar o SimpleCursorAdapter diretamente
 */
public class ListaContatosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "livroandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        final ListView listView = (ListView) findViewById(br.com.livroandroid.contatos.R.id.listView);
        listView.setOnItemClickListener(this);

        // Lista os contatos
        Uri contatos = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contatos, null, ContactsContract.Contacts.HAS_PHONE_NUMBER +" = 1 ", null, ContactsContract.Contacts.DISPLAY_NAME);

        // Não temos como jogar a foto aqui
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.adapter_contato,
                cursor,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{R.id.tNome},
                0);
        listView.setAdapter(adapter);

        Toast.makeText(this, "Com o SimpleCursorAdapter diretamente não conseguimos mostrar a foto.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Agenda a = new Agenda(this);
        Contato c = a.getContatoById(id);
        Toast.makeText(this, "Ex2: " + c.nome, Toast.LENGTH_SHORT).show();
        c.show(this);
    }
}
