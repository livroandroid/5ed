package br.com.livroandroid.helloalarme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ricardo on 13/03/15.
 */
public class AlarmUtil {
    private static final String TAG = "livroandroid";

    // Agenda o alarme
    public static void schedule(Context context, Intent intent, long triggerAtMillis) {
        PendingIntent p = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, p);
        Log.d("livroandroid-alarm", "Alarme agendado com sucesso.");
    }

    // Agenda o alarme com repeat
    public static void scheduleRepeat(Context context, Intent intent, long triggerAtMillis, long intervalMillis) {
        PendingIntent p = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarme.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, p);
        Log.d("livroandroid-alarm", "Alarme agendado com sucesso com repeat.");
    }

    public static void cancel(Context context, Intent intent) {
        AlarmManager alarme = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent p = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarme.cancel(p);
        Log.d("livroandroid-alarm", "Alarme cancelado com sucesso.");
    }
}
