package br.com.livroandroid.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Usuário on 28/03/2015.
 */
public class CarroAdapter extends CursorAdapter {

    private int[] indices;

    public CarroAdapter(Context context) {
        super(context, null, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_carro, parent,false);
        indices = new int[] {
                cursor.getColumnIndex("_id"),
                cursor.getColumnIndex("nome"),
                cursor.getColumnIndex("url_foto")
        };
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tNome = (TextView) view.findViewById(R.id.tNome);
        ImageView img = (ImageView) view.findViewById(R.id.img);

        String nome = cursor.getString(indices[1]);
        String urlFoto = cursor.getString(indices[2]);

        Log.d("livroandroid","Carro: " + nome + " - " + urlFoto);

        tNome.setText(nome);
        Picasso.with(mContext).load(urlFoto).into(img);

    }
}