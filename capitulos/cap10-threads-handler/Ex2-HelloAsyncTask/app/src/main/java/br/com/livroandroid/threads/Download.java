package br.com.livroandroid.threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Ricardo Lecheta on 21/09/2014.
 */
public class Download {
    public static Bitmap downloadBitmap(String url) throws IOException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        // Faz o download da imagem
        Bitmap bitmap = null;
        InputStream in = new URL(url).openStream();
        // Converte a InputStream do Java para Bitmap
        bitmap = BitmapFactory.decodeStream(in);
        in.close();
        return bitmap;
    }
}
