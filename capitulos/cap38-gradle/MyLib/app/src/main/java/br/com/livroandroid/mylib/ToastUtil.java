package br.com.livroandroid.mylib;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Usuário on 16/04/2015.
 */
public class ToastUtil {
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
