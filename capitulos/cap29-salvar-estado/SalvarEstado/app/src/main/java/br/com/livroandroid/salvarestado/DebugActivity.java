package br.com.livroandroid.salvarestado;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

/**
 * Created by Ricardo Lecheta on 22/09/2014.
 */
public class DebugActivity extends ActionBarActivity {
    protected static final String TAG = "livro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClassName() + ".onCreate(): " + savedInstanceState);
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, getClassName() + ".onStart().");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, getClassName() + ".onRestart().");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, getClassName() + ".onResume().");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, getClassName() + ".onSaveInstanceState().");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, getClassName() + ".onPause().");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, getClassName() + ".onStop().");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getClassName() + ".onDestroy().");
    }

    private String getClassName() {
        // Retorna o nome da classe sem o pacote
        Class cls = ((Object) this).getClass();
        String s = cls.getSimpleName();
        return s;
    }
}
