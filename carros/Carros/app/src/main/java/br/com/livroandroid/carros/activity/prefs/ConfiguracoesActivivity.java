package br.com.livroandroid.carros.activity.prefs;

import android.os.Bundle;

import br.com.livroandroid.carros.R;

@SuppressWarnings("deprecation")
public class ConfiguracoesActivivity extends android.preference.PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carrega as configurações
        addPreferencesFromResource(R.xml.preferences);
    }
}
