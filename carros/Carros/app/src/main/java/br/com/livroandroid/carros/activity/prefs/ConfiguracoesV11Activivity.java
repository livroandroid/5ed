package br.com.livroandroid.carros.activity.prefs;

import android.app.FragmentTransaction;
import android.os.Bundle;

import br.com.livroandroid.carros.R;

public class ConfiguracoesV11Activivity extends android.app.Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        // Adiciona o fragment de configurações
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, new PrefsFragment());
        ft.commit();
    }

    public static class PrefsFragment extends android.preference.PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Carrega as configurações
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
