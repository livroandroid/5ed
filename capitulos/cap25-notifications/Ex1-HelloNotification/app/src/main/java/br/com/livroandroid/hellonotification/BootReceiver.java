package br.com.livroandroid.hellonotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ricardo on 23/03/15.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("livroandroid","BootReceiver");
        final int id = 1;
        final Intent intent2 = new Intent(context,MensagemActivity.class);
        intent.putExtra("msg","Olá Leitor, como vai?");
        final String contentTitle = "Nova mensagem privada";
        final String contentText = "Você possui uma nova mensagem privada";
        Toast.makeText(context, "Desligue o cell e aguarde na tela de bloqueio", Toast.LENGTH_SHORT).show();
        NotificationUtil.createPrivateNotification(context, intent2, contentTitle, contentText, id);
    }
}
