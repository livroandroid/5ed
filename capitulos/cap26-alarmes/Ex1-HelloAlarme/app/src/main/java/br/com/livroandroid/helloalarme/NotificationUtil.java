package br.com.livroandroid.helloalarme;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.List;

/**
 * Classe utilitária para criar notificações.
 *
 * http://developer.android.com/guide/topics/ui/notifiers/notifications.html
 */
public class NotificationUtil {

    public static final String ACTION_VISUALIZAR = "br.com.livroandroid.hellonotification.ACTION_VISUALIZAR";

    static final String CHANNEL_ID = "1";

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

    public static void create(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context,CHANNEL_ID);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.mipmap.ic_launcher); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        b.setColor(Color.GREEN);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    private static PendingIntent getPendingIntent(Context context, Intent intent, int id) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }

    public static void cancell(Context context, int id) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(id);
    }

    public static void cancellAll(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }
}
