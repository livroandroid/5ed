package br.com.livroandroid.wearnotification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;

/**
 * Created by ricardo on 21/04/15.
 */
public class NotificationWearUtil {
    public static void createPagesNotification(Context context) {
        // Página 1
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Página 1")
                        .setContentText("Primeira mensagem")
                        /*.setContentIntent(viewPendingIntent)*/;

        // Página 2
        Notification page2 =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Página 2")
                        .setContentText("Segunda mensagem")
                        .build();

        // Cria as páginas
        Notification notification = notificationBuilder
                .extend(new NotificationCompat.WearableExtender()
                        .addPage(page2))
                .build();

        // Dispara a notificação
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(1, notification);
    }

    public static void createRemoteInputNotification(Context context, Intent replyIntent) {
        // Intent para executar ao responder
        PendingIntent replyPendingIntent =
                PendingIntent.getActivity(context, 0, replyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Remote Input
        String[] replyChoices = context.getResources().getStringArray(R.array.reply_choices);
        RemoteInput remoteInput = new RemoteInput.Builder("remote.input.key")
                .setLabel("Resposta")
                .setChoices(replyChoices)
                .build();
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_action_reply,
                        "Responder", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        // Cria a notificação
        Notification notification =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Lembrete")
                        .setContentText("Você vai a festa?")
                        .extend(new NotificationCompat.WearableExtender().addAction(action))
                        .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);
    }
}
