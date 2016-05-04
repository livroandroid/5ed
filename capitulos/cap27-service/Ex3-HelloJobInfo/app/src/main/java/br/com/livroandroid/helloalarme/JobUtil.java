package br.com.livroandroid.helloalarme;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

/**
 * Created by ricardo on 25/03/15.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobUtil {

    public static void schedule(Context context, Class<?> cls, int id) {
        // JobService que vai executar
        ComponentName mServiceComponent = new ComponentName(context, cls);
        JobInfo.Builder builder = new JobInfo.Builder(id,mServiceComponent);

        // Wi-Fi
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        // Carregando
        builder.setRequiresCharging(true);

        // Agenda o job
        JobScheduler jobScheduler =
                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo job = builder.build();
        jobScheduler.schedule(job);
    }

    public static void cancel(Context context, int id) {
        JobScheduler jobScheduler =
                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(id);
    }

    public static void cancelAll(Context context) {
        JobScheduler jobScheduler =
                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancelAll();

    }
}
