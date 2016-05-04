package br.com.livroandroid.sensores;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;

import java.util.concurrent.TimeUnit;


public class GoogleFitPedometroActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "livroandroid";
    private static final int REQUEST_OAUTH = 1;
    /**
     * Controla se o dialog de autorização está aberto
     */
    private static final String AUTH_PENDING = "auth_state_pending";
    private GoogleApiClient mGoogleApiClient;
    private TextView text;
    private int qtdePassos;
    private boolean authInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_fit_pedometro);

        text = (TextView) findViewById(R.id.text);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            authInProgress = savedInstanceState.getBoolean(AUTH_PENDING);
        }

        // Configura o objeto GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.SENSORS_API)
                .addScope(new Scope(Scopes.FITNESS_LOCATION_READ))
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Conecta no Google Play Services
        if (!mGoogleApiClient.isConnecting() && !mGoogleApiClient.isConnected()) {
            toast("mGoogleApiClient.connect()");
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Desconecta ao sair
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        toast("Conectado no Google Play Services!");

        startPedometer();
    }

    private void startPedometer() {
        Log.d("livroandroid", "startPedometer");
        // Listener do Fitness API que conta os passos
        OnDataPointListener listener = new OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                for (Field field : dataPoint.getDataType().getFields()) {
                    if (dataPoint.getDataType().equals(DataType.TYPE_STEP_COUNT_DELTA)) {
                        Value val = dataPoint.getValue(field);
                        Log.d("livroandroid", "Valor Pedometro: " + val);
                        qtdePassos += val.asInt();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText("Passos: " + qtdePassos);
                            }
                        });
                    }
                }
            }
        };

        // Contador de passos (TYPE_STEP_COUNT_DELTA)
        SensorRequest req = new SensorRequest.Builder()
                .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
                .setSamplingRate(1, TimeUnit.SECONDS)
                .build();

        // Ativa a API do Fitness
        PendingResult<Status> result = Fitness.SensorsApi.add(mGoogleApiClient, req, listener);
        toast("Pedômetro do Google Fit ativado: " + result);
    }

    @Override
    public void onConnectionSuspended(int cause) {
        toast("Conexão interrompida.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            // Se for algum erro de configuração ou serviço mostra alerta
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
            return;
        }
        // Caso contrário pode ser porque o usuário não autorizou o acesso.
        if (!authInProgress) {
            try {
                Log.i(TAG, "Attempting to resolve failed connection");
                authInProgress = true;
                result.startResolutionForResult(GoogleFitPedometroActivity.this,
                        REQUEST_OAUTH);
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG,
                        "Exception while starting resolution activity", e);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_OAUTH) {
            authInProgress = false;
            if (resultCode == RESULT_OK) {
                // Depois que o usuário autorizou faz login no Google Play Services
                if (!mGoogleApiClient.isConnecting() && !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AUTH_PENDING, authInProgress);
    }

    private void toast(String s) {
        Log.d("livroandroid", "> " + s);
        Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
    }
}
