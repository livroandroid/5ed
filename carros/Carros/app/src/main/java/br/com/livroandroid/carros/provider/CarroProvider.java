package br.com.livroandroid.carros.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

import java.util.HashMap;

import br.com.livroandroid.carros.domain.CarroDB;

/**
 * Created by rlech on 10/10/2015.
 */
public class CarroProvider extends ContentProvider {
    // Classe que contém o banco de dados do carro
    private CarroDB db;
    // Colunas para selecionar
    private static HashMap<String, String> colunas;
    private static final int CARROS = 1;
    private static final int CARROS_ID = 2;
    // Utilizada para validar as expressões regulares sobre a URI
    private UriMatcher uriCarro;
    // Para criar a URI: content://br.com.livroandroid.carros/carros
    private static String getAuthority() {
        return "br.com.livroandroid.carros";
    }
    public static final class Carros implements BaseColumns {
        // Não pode instanciar esta Classe
        private Carros() { }
        // content://br.com.livroandroid.carros/carros
        public static final Uri CONTENT_URI = Uri.parse("content://"+getAuthority()+"/carros");

        // Mime Type para todos os carros
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.carros";
        // Mime Type para um único carro
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.carros";
        // Ordenação default para inserir no order by
        public static final String DEFAULT_SORT_ORDER = "_id ASC";
        public static final String NOME = "nome";
        public static final String DESC = "desc";
        public static final String URL_INFO = "url_info";
        public static final String URL_FOTO = "url_foto";
        public static final String URL_VIDEO = "url_video";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String TIPO = "tipo";
        // Método que constrói uma URI para um carro específico, com o seu id
        // A URI é no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriCarro = ContentUris.withAppendedId(Carros.CONTENT_URI, id);
            return uriCarro;
        }
    }
    @Override
    public boolean onCreate() {
        // Uri Matcher para fazer as expressões regulares
        uriCarro = new UriMatcher(UriMatcher.NO_MATCH);
        // content://br.livro.android.provider.carro/carros
        uriCarro.addURI(getAuthority(), "carros", CARROS);
        // content://br.livro.android.provider.carro/carros/id
        uriCarro.addURI(getAuthority(), "carros/#", CARROS_ID);
        // Colunas para selecionar
        colunas = new HashMap<String, String>();
        colunas.put(Carros._ID, Carros._ID);
        colunas.put(Carros.NOME, Carros.NOME);
        colunas.put(Carros.DESC, Carros.DESC);
        colunas.put(Carros.URL_INFO, Carros.URL_INFO);
        colunas.put(Carros.URL_FOTO, Carros.URL_FOTO);
        colunas.put(Carros.URL_VIDEO, Carros.URL_VIDEO);
        colunas.put(Carros.LATITUDE, Carros.LATITUDE);
        colunas.put(Carros.LONGITUDE, Carros.LONGITUDE);
        colunas.put(Carros.TIPO, Carros.TIPO);
        db = new CarroDB(getContext());
        return true;
    }
    @Override
    // Retorna o MIME type correto
    public String getType(Uri uri) {
        switch (uriCarro.match(uri)) {
            case CARROS:
                return Carros.CONTENT_TYPE;
            case CARROS_ID:
                return Carros.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
    }
    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        // Valida se a URI é de /carros
        if (uriCarro.match(uri) != CARROS) {
            throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }
        long id = db.insert(values);
        if (id > 0) {
            // Inseriu
            Uri uriCarro = Carros.getUriId(id);
            getContext().getContentResolver().notifyChange(uriCarro, null);
            // Retorna a URI com o id do carro inserido
            return uriCarro;
        }
        throw new SQLException("Falhou ao inserir " + uri);
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[]
            selectionArgs, String sortOrder) {
        // Classe utilitária para criar queries no SQLite
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        if(projection == null) {
            projection = new String[] {"_id",Carros.NOME,Carros.DESC,Carros.URL_INFO,
                    Carros.URL_FOTO,Carros.URL_VIDEO,Carros.LATITUDE,Carros.LONGITUDE,Carros.TIPO};
        }
        // Configura a tabela para busca e a projeção
        switch (uriCarro.match(uri)) {
            case CARROS:
                builder.setTables("carro");
                builder.setProjectionMap(colunas);
                break;
            case CARROS_ID:
                builder.setTables("carro");
                builder.setProjectionMap(colunas);
                // where _id=?
                builder.appendWhere(Carros._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
        // Ordenação
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = Carros.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }
        // Query
        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor c = builder.query(db, projection, selection, selectionArgs, null, null, orderBy);
        // Notifica o content provider
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        int count;
        switch (uriCarro.match(uri)) {
            case CARROS:
                count = db.update(values, where, whereArgs);
                break;
            case CARROS_ID:
                // id do carro
                String id = uri.getPathSegments().get(1);
                // Atualiza o where se informado com o "_id=?" para atualizar
                String whereFinal = Carros._ID + "=" + id + (!TextUtils.isEmpty(where)
                        ? " AND (" + where + ')' : "");
                count = db.update(values, whereFinal, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
        // Notifica o content provider
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        int count;
        switch (uriCarro.match(uri)) {
            case CARROS:
                count = db.delete(where, whereArgs);
                break;
            case CARROS_ID:
                // id do carro
                String id = uri.getPathSegments().get(1);
                // Atualiza o where se informado com o "_id=?" para atualizar
                String whereFinal = Carros._ID + "=" + id + (!TextUtils.isEmpty(where)
                        ? " AND (" + where + ')' : "");
                count = db.delete(whereFinal, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}

