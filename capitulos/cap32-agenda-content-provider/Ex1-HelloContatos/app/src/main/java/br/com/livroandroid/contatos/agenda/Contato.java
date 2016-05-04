package br.com.livroandroid.contatos.agenda;

import java.io.InputStream;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;

/**
 * Representa um contato da agenda
 * 
 * @author ricardo
 * 
 */
public class Contato {

	public long id;
	public String nome;
	public Bitmap foto;
	public List<String> fones;

	// Retorna a URI deste contato, ex: "content://com.android.contacts/contacts/1"
	public Uri getUri() {
		Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
		return uri;
	}

    // Le a foto de um contato
    public Bitmap getFoto(Context context) {
        // Cria a Uri para o id fornecido
        Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
        ContentResolver contentResolver = context.getContentResolver();
        InputStream in = Contacts.openContactPhotoInputStream(contentResolver, uri);
        if (in != null) {
            Bitmap foto = BitmapFactory.decodeStream(in);
            return foto;
        }
        return null;
    }

    public void show(Context context) {
        Uri uriContato = getUri();
        context.startActivity(new Intent(Intent.ACTION_VIEW,uriContato));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contato contato = (Contato) o;

        if (id != contato.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
