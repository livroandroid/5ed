package br.com.livroandroid.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Utiliza o CursorLoader para ler o Content Provider dos carros
 */
public class CarrosFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    Uri CARROS_URI = Uri.parse("content://br.com.livroandroid.carros/carros");

    protected ListView listView;
    private String tipo;

    private CursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros, container, false);

        // Configura o ListView
        ListView listView = (ListView) view.findViewById(R.id.listView);
        adapter = new CarroAdapter(getActivity());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        // Inicia o loader
        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getActivity(),
                CARROS_URI, // URI
                new String[] { "_id", "nome","url_foto"}, // colunas
                "tipo=?", // where
                new String[]{tipo}, // where args
                null // order by
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Carrega o adapter com os dados do Cursor
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Limpa o adapter
        adapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = getActivity().getContentResolver().query(CARROS_URI, null, "_id=?", new String[]{String.valueOf(id)}, null);
        Carro carro = CarroService.getCarro(cursor);
        if(carro != null) {
            Toast.makeText(getActivity(), "Carro: " + carro.nome, Toast.LENGTH_SHORT).show();
        }
        // TODO Continue o desenvolvimento aqui...
    }
}