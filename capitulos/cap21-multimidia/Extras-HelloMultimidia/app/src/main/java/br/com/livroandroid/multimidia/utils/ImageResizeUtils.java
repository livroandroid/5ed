package br.com.livroandroid.multimidia.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

/**
 * Faz o resize de uma imagem de forma eficiente
 *
 * http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
 *
 * @author Ricardo Lecheta
 */
public class ImageResizeUtils {

    private static final String TAG = ImageResizeUtils.class.getName();

    public static Bitmap getResizedImageResource(Context context,int resImgId, int width, int height) {
        try {
            // Configura o BitmapFactory para apenas ler o tamanho da imagem (sem carregá-la em memória)
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            // Faz o decode da imagem
            BitmapFactory.decodeResource(context.getResources(), resImgId, opts);
            // Lê a largura e altura do arquivo
            int w = opts.outWidth;
            int h = opts.outHeight;

            if (width == 0 || height == 0) {
                width = w / 2;
                height = h / 2;
            }

            Log.d(TAG, "Resize img, w:" + w + " / h:" + h + ", to w:" + width + " / h:" + height);

            // Fator de escala
            int scaleFactor = Math.min(w / width, h / height);
            opts.inSampleSize = scaleFactor;
            Log.d(TAG, "inSampleSize:" + opts.inSampleSize);
            // Agora deixa carregar o bitmap completo
            opts.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resImgId, opts);

            Log.d(TAG, "Resize OK, w:" + bitmap.getWidth() + " / h:" + bitmap.getHeight());

            return bitmap;

        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    public static Bitmap getResizedImage(Uri uriFile, int width, int height) {
        return getResizedImage(uriFile, width, height, false);
    }

    public static Bitmap getResizedImage(Uri uriFile, int width, int height, boolean fixMatrix) {
        try {
            // Configura o BitmapFactory para apenas ler o tamanho da imagem (sem carregá-la em memória)
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            // Faz o decode da imagem
            BitmapFactory.decodeFile(uriFile.getPath(), opts);
            // Lê a largura e altura do arquivo
            int w = opts.outWidth;
            int h = opts.outHeight;

            if (width == 0 || height == 0) {
                width = w / 2;
                height = h / 2;
            }

            Log.d(TAG, "Resize img, w:" + w + " / h:" + h + ", to w:" + width + " / h:" + height);

            // Fator de escala
            int scaleFactor = Math.min(w / width, h / height);
            opts.inSampleSize = scaleFactor;
            Log.d(TAG, "inSampleSize:" + opts.inSampleSize);
            // Agora deixa carregar o bitmap completo
            opts.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(uriFile.getPath(), opts);

            Log.d(TAG, "Resize OK, w:" + bitmap.getWidth() + " / h:" + bitmap.getHeight());

            if (fixMatrix) {
                Bitmap newBitmap = fixMatrix(uriFile, bitmap);

                bitmap.recycle();

                return newBitmap;
            } else {
                return bitmap;
            }

        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    private static Bitmap fixMatrix(Uri uriFile, Bitmap bitmap) throws IOException {
        Matrix matrix = new Matrix();

        /**
         * Classe para ler tags escritas no JPEG
         * Para utilizar esta classe precisa de Android 2.2 ou superior
         */
        ExifInterface exif = new ExifInterface(uriFile.getPath());

        // Lê a orientação que foi salva a foto
        int orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);

        boolean fix = false;

        // Rotate bitmap
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                fix = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                fix = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                fix = true;
                break;
            default:
                // ORIENTATION_ROTATE_0
                fix = false;
                break;
        }

        if (!fix) {
            return bitmap;
        }

        // Corrige a orientação (passa a matrix)
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        bitmap.recycle();
        bitmap = null;

        return newBitmap;
    }
}
