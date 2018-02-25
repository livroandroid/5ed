package br.com.livroandroid.helloalarme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by ricardo on 08/03/15.
 */
public class LembremeDeComerReceiver extends BroadcastReceiver {

    private static final String TAG = "livroandroid";
    public static final String ACTION = "br.com.livroandroid.helloalarme.LEMBREME_DE_COMER";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Você precisa comer: " + new Date());

        Intent notifIntent = new Intent(context,MainActivity.class);

        NotificationUtil.create(context, notifIntent,"Hora de comer algo...","Que tal uma fruta?",1);
    }
}
