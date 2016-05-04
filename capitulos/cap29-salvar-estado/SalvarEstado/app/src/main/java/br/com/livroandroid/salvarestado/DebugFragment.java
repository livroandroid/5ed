package br.com.livroandroid.salvarestado;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ricardo Lecheta on 22/09/2014.
 */
public class DebugFragment extends Fragment {
    protected static final String TAG = "livro";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClassName() + ".onCreate().");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, getClassName() + ".onCreateView().");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, getClassName() + ".onActivityCreated().");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, getClassName() + ".onStart().");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, getClassName() + ".onResume().");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, getClassName() + ".onSaveInstanceState().");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, getClassName() + ".onPause().");
    }

    public void onStop() {
        super.onStop();
        Log.i(TAG, getClassName() + ".onStop().");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, getClassName() + ".onDetach().");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, getClassName() + ".onDestroyView().");
    }

    public void onDestroy() {
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
