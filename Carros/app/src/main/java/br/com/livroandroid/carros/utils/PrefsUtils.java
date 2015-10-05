package br.com.livroandroid.carros.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefsUtils {
    // Verifica se o usuário marcou o checkbox de Push ON nas configurações
    public static boolean isCheckPushOn(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean("PREF_CHECK_PUSH", false);
    }
}
