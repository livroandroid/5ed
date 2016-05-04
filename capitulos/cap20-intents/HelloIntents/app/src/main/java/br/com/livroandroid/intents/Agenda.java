package br.com.livroandroid.intents;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa com o Content Provider do Android 2.x
 * <p>
 * content://com.android.contacts/contacts
 *
 * @author ricardo
 */
public class Agenda {
    // content://com.android.contacts/contacts
    private static final Uri URI = Contacts.CONTENT_URI;

    public static Contato getContato(Context context, Uri contactUri) {
        // Busca o contato no banco de dados
        Cursor cursor = context.getContentResolver().query(contactUri, null, null, null, null);

        try {
            if (cursor.moveToNext()) {
                Contato c = lerContato(context, cursor);
                return c;
            }
        } finally {
            cursor.close();
        }

        return null;
    }

    public static List<Contato> getContatos(Context context) {

        List<Contato> contatos = new ArrayList<Contato>();

        // Recupera o Cursor para percorrer a lista de contatos
        Cursor c = context.getContentResolver().query(URI, null, null, null,
                null);

        try {
            while (c.moveToNext()) {
                Contato a = lerContato(context, c);
                contatos.add(a);
            }
        } finally {
            // Fecha o Cursor
            c.close();
        }

        return contatos;
    }

    public static Contato lerContato(Context context, Cursor cursor) {
        Contato c = new Contato();

        // Id e nome
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(Contacts._ID));
        c.id = id;

        String nome = cursor.getString(cursor.getColumnIndexOrThrow(Contacts.DISPLAY_NAME));
        c.nome = nome;

        // Fone
        boolean temFone = "1".equals(cursor.getString(cursor.getColumnIndexOrThrow(Contacts.HAS_PHONE_NUMBER)));
        if (temFone) {
            List<String> fones = loadFones(context, id);
            c.fones = fones;
        }

        // Email
        c.emails = loadEmails(context, id);

        return c;
    }

    private static List<String> loadEmails(Context context, long id) {
        List<String> emails = new ArrayList<String>();

        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + id,
                null, null);

        try {
            while (cursor.moveToNext()) {
                int coluna = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                String email = cursor.getString(coluna);
                emails.add(email);
            }
        } finally {
            cursor.close();
        }

        return emails;
    }

    // Busca os telefones na tabela 'ContactsContract.CommonDataKinds.Phone'
    private static List<String> loadFones(Context context, long id) {
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
}