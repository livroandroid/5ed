package br.com.livroandroid.intents;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;

import java.io.InputStream;
import java.util.List;

/**
 * Representa um contato da agenda
 *
 * @author ricardo
 */
public class Contato {

    public long id;
    public String nome;
    public Uri foto;
    public List<String> fones;
    public List<String> emails;

    // Retorna a URI deste contato, ex: "content://com.android.contacts/contacts/1"
    public Uri getUri() {
        Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
        return uri;
    }

    public Bitmap getFoto(Context context) {
        Uri uri = getUri();
        ContentResolver contentResolver = context.getContentResolver();
        InputStream in = Contacts.openContactPhotoInputStream(contentResolver, uri);
        if (in != null) {
            Bitmap foto = BitmapFactory.decodeStream(in);
            return foto;
        }
        return null;
    }
}
