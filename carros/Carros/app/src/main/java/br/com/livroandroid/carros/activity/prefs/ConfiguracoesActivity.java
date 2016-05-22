package br.com.livroandroid.carros.activity.prefs;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.PreferenceFragmentCompat;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.BaseActivity;

public class ConfiguracoesActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Adiciona o fragment de configurações
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, new PrefsFragment());
            ft.commit();
        }
    }

    // Fragment que carrega o layout com as configurações
    public static class PrefsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            // Carrega as configurações
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}