package br.com.livroandroid.playermp3;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import br.com.livroandroid.playermp3.utils.NotificationUtil;
import br.com.livroandroid.playermp3.utils.PermissionUtils;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "livro";
    // Classe que encapsula o MediaPlayer

    private EditText text;

    private InterfaceMp3 interfaceMp3;
    private ServiceConnection conexao = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // (*3*)
            // Recupera a interface para interagir com o servico
            Mp3Service.Mp3ServiceBinder conexao = (Mp3Service.Mp3ServiceBinder) service;
            interfaceMp3 = conexao.getInterface();
            Log.d(TAG, "onServiceConnected, interfaceMp3 conectada: " + interfaceMp3);
        }
        public void onServiceDisconnected(ComponentName className) {
            // (*6*)
            Log.d(TAG, "onServiceDisconnected, liberando recursos.");
            interfaceMp3 = null;
        }
    };
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.tArquivo);

        NotificationUtil.createChannel(this);

        Intent intent = new Intent(this,Mp3Service.class);
        Log.d(TAG, "Iniciando o service");
        // (*1*)
        startService(intent);
        // Faz o bind
        // (*2*)
        boolean b = bindService(intent, conexao, Context.BIND_AUTO_CREATE);
        Log.d(TAG,"Service conectado: " + b);

        // Solicita as permissões
        String[] permissoes = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        PermissionUtils.validate(this, 0, permissoes);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Alguma permissão foi negada, agora é com você :-)
                alertAndFinish();
                return;
            }
        }
        // Se chegou aqui está OK :-)
    }

    private void alertAndFinish() {
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões.");
            // Add the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            android.support.v7.app.AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

    public void onClickPlay(View view) {
        // (*4*)
        if(interfaceMp3 != null) {
            String mp3 = text.getText().toString();
            Log.d(TAG,"play: " +  mp3);
            interfaceMp3.play(mp3);
        }
    }
    public void onClickPause(View view) {
        // (*4*)
        if(interfaceMp3 != null) {
            Log.d(TAG,"pause");
            interfaceMp3.pause();
        }
    }
    public void onClickStop(View view) {
        // (*4*)
        if(interfaceMp3 != null) {
            Log.d(TAG, "stop");
            interfaceMp3.stop();
        }
        NotificationUtil.cancell(this,1);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(interfaceMp3 != null && interfaceMp3.isPlaying()) {
            // (*5*)
            Log.d(TAG, "Activity destruida. A musica continua.");
            unbindService(conexao);
            // Cria a notificacao para o usuario voltar ao player.
            String mp3 = interfaceMp3.getMp3();
            NotificationUtil.create(this, new Intent(this, MainActivity.class),"MP3 Player", mp3, 1);
        } else {
            // (*7*)
            Log.d(TAG, "Activity destruida. Para o servico, pois nao existe musica tocando.");
            unbindService(conexao);
            stopService(new Intent(this, Mp3Service.class));
        }
    }
}
