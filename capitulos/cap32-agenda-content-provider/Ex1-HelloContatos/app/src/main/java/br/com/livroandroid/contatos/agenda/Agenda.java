package br.com.livroandroid.contatos.agenda;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Busca os contatos na agenda.
 * <p/>
 * content://com.android.contacts/contacts
 *
 * @author ricardo
 */
public class Agenda {
    // content://com.android.contacts/contacts
    private static final Uri URI = Contacts.CONTENT_URI;
    private static final String TAG = "agenda";
    private Context context;

    public Agenda(Context context) {
        this.context = context;
    }

    public List<Contato> getContatos() {

        // Recupera o Cursor para percorrer a lista de contatos
        Cursor c = getCursorContatos();

        return getContatos(c);
    }

    public Cursor getCursorContatos() {
        return context.getContentResolver().query(URI, null, ContactsContract.Contacts.HAS_PHONE_NUMBER + " = 1 ", null, Contacts.DISPLAY_NAME);
    }

    public List<Contato> getContatos(Cursor cursor) {
        List<Contato> contatos = new ArrayList<Contato>();

        try {
            while (cursor.moveToNext()) {
                Contato a = getContato(cursor);
                contatos.add(a);
            }
        } finally {
            // Fecha o Cursor
            cursor.close();
        }

        return contatos;
    }

    public Contato getContatoById(Long id) {
        Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
        Cursor cursor = context.getContentResolver().query(uri, null, "has_phone_number=1", null, null);
        if (cursor.moveToNext()) {
            Contato c = getContato(cursor);
            return c;
        }
        return null;
    }

    public Contato getContato(Cursor cursor) {
        Contato c = new Contato();

        // Id e nome
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(Contacts._ID));
        c.id = id;

        String nome = cursor.getString(cursor.getColumnIndexOrThrow(Contacts.DISPLAY_NAME));
        c.nome = nome;

        // Fone
        boolean temFone = "1".equals(cursor.getString(cursor.getColumnIndexOrThrow(Contacts.HAS_PHONE_NUMBER)));
        if (temFone) {
            List<String> fones = getFones(id);
            c.fones = fones;
        }
        return c;
    }

    // Busca os telefones na tabela 'ContactsContract.CommonDataKinds.Phone'
    private List<String> getFones(long id) {
        List<String> fones = new ArrayList<String>();

        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null, null);

        try {
            while (cursor.moveToNext()) {
                int coluna = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String fone = cursor.getString(coluna);
                fones.add(fone);
            }
        } finally {
            cursor.close();
        }

        return fones;
    }

    public boolean addContato(String nome, String telefone, int imgFoto) {
        try {

            // Lista de operações para executar no provedor de conteúdo
            ArrayList<ContentProviderOperation> operation =
                    new ArrayList<ContentProviderOperation>();

            int backRefIndex = 0;

            // Adiciona o contato
            operation.add(
                    ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).
                            withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).
                            withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());

            // Nome do contato
            operation.add(
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).
                            withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, backRefIndex).
                            withValue(ContactsContract.Data.MIMETYPE,
                                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE).
                            withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                                    nome).build());

            // Associa o telefone do tipo "Home" ao contato
            operation.add(
                    ContentProviderOperation.newInsert(
                            ContactsContract.Data.CONTENT_URI).
                            withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                                    backRefIndex).
                            withValue(ContactsContract.Data.MIMETYPE,
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE).
                            withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    telefone).
                            withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                    ContactsContract.CommonDataKinds.Phone.TYPE_HOME).
                            build());

            // Foto do contato
            Bitmap fotoBitmap = BitmapFactory.decodeResource(context.getResources(), imgFoto);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            fotoBitmap.compress(Bitmap.CompressFormat.PNG, 75, stream);

            operation.add(
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, backRefIndex)
                            .withValue(ContactsContract.Data.IS_SUPER_PRIMARY, 1)
                            .withValue(ContactsContract.Data.MIMETYPE,
                                    ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, stream.toByteArray())
                            .build());

            // Executa a lista de operações em batch
            context.getContentResolver().applyBatch(
                    ContactsContract.AUTHORITY, operation);

            return true;

        } catch (Exception e) {
            Log.d(TAG, "Erro ao inserir contato: " + e.getMessage(), e);
        }

        return false;
    }

    public int delete(Contato c) {
        return delete(c.id);
    }

    public int delete(Long id) {
        Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
        int count = context.getContentResolver().delete(uri,null,null);
        return count;
    }
}