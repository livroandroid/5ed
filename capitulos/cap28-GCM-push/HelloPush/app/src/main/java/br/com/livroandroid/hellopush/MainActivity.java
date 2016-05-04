package br.com.livroandroid.hellopush;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import livroandroid.lib.utils.NotificationUtil;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "livroandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verifica se o Google Play Services está instalado
        boolean ok = checkPlayServices();
        if (ok) {
            // Já está registrado
            String regId = GCM.getRegistrationId(this);
            setText(regId);

            // Quando iniciar, lê a msg da notification
            String msg = getIntent().getStringExtra("msg");
            setText(msg);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Quando iniciar, lê a msg da notification
        String msg = intent.getStringExtra("msg");
        setText(msg);
    }

    private Context getContext() {
        return this;
    }

    // Mostra msg de debug na tela
    private void setText(final String s) {
        if(s != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView text = (TextView) findViewById(R.id.text);
                    text.setText(s);
                    Log.d(TAG,s);
                }
            });
        }
    }

    // Faz o registro no GCM
    public void onClickRegistrar(View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                String regId = GCM.getRegistrationId(getContext());
                if (regId == null) {
                    // Faz o registro e pega o registration id
                    regId = GCM.register(getContext(), Constants.PROJECT_NUMBER);
                    setText("Registrado com sucesso.\n" + regId);
                } else {
                    toast("Você já está registrado.");
                }
            }
        }.start();
    }

    // Cancela o registro no GCM
    public void onClickCancelar(View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                GCM.unregister(getContext());
                setText("Cancelado com sucesso!");
            }
        }.start();
    }

    /**
     * Verifica se o Google Play Services está instalado
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                toast("Este aparelho não suporta o Google Play Services.");
                finish();
            }
            return false;
        }
        return true;
    }

    private void toast(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Toast: " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
