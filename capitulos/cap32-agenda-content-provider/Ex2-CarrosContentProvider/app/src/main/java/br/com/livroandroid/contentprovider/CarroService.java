package br.com.livroandroid.contentprovider;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuário on 28/03/2015.
 */
public class CarroService {

    public static Carro getCarro(Cursor cursor) {
        Carro c = new Carro();
        boolean ok = cursor.moveToNext();
        if(ok) {
            read(cursor, c);
        }
        return c;
    }

    // Lê o cursor e cria a lista de carros
    private static List<Carro> toList(Cursor cursor) {
        List<Carro> carros = new ArrayList<Carro>();

        if (cursor.moveToFirst()) {
            do {
                Carro carro = new Carro();
                read(cursor, carro);
                carros.add(carro);

            } while (cursor.moveToNext());
        }

        return carros;
    }

    // Faz a leitura os atributos de carro
    private static void read(Cursor c, Carro carro) {

        Log.d("livroandroid","idx : " + c.getColumnIndex("_id") + " - " + c.getColumnCount() + " - " + c.getColumnName(0));

        carro.id = c.getLong(c.getColumnIndex("_id"));
        carro.nome = c.getString(c.getColumnIndex("nome"));
        carro.desc = c.getString(c.getColumnIndex("desc"));
        carro.urlInfo = c.getString(c.getColumnIndex("url_info"));
        carro.urlFoto = c.getString(c.getColumnIndex("url_foto"));
        carro.urlVideo = c.getString(c.getColumnIndex("url_video"));
        carro.latitude = c.getString(c.getColumnIndex("latitude"));
        carro.longitude = c.getString(c.getColumnIndex("longitude"));
        carro.tipo = c.getString(c.getColumnIndex("tipo"));
    }
}
