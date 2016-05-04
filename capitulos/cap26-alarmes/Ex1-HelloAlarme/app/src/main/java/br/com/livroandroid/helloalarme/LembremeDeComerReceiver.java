package br.com.livroandroid.helloalarme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Date;

import livroandroid.lib.utils.NotificationUtil;

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

        NotificationUtil.create(context, 1, notifIntent, R.mipmap.ic_launcher,"Hora de comer algo...","Que tal uma fruta?");
    }
}
