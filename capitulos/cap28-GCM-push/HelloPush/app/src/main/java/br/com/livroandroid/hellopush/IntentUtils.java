package br.com.livroandroid.hellopush;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.Html;
import android.util.Log;

import java.util.List;

/**
 * Classe utilitária para enviar intents
 */
public class IntentUtils {

    private static final String TAG = "IntentUtils";

    public static void sendEmail(Context context, String toEmail, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("plain/text");// message/rfc822
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { toEmail });
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(message));
        context.startActivity(intent);
    }
}
