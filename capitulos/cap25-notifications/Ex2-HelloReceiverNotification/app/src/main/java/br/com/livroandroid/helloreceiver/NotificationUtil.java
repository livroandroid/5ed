package br.com.livroandroid.helloreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Classe utilitária para disparar notifications
 */
public class NotificationUtil {

    private static final String TAG = "livroandroid";

    static final String CHANNEL_ID = "1";

    // Cria o channel necessário para Android 8
    public static void createChannel(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel c = new NotificationChannel(CHANNEL_ID,
                    context.getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
            c.setLightColor(Color.BLUE);
            c.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
//            c.enableVibration(false);
//            c.enableLights(false);
//            c.setSound();
            manager.createNotificationChannel(c);
        }
    }

    public static void notify(Context context, int id, Intent intent, String contentTitle, String contentText) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Intent para disparar o broadcast
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Cria a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setContentIntent(p)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        // Dispara a notification
        Notification n = builder.build();
        manager.notify(id, n);

        Log.d(TAG,"Notification criada com sucesso");
    }
}
