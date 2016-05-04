package br.com.livroandroid.bluetooth;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Sistemas de permissão do Android 6.0
 *
 * http://developer.android.com/preview/features/runtime-permissions.html
 */
public class PermissionUtils {

    /**
     * Verifica se tem a permissão
     *
     * @param activity
     * @param permission
     * @return
     */
    public static boolean checkPermission(Activity activity,String permission) {
        boolean ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
        return ok;
    }

    /**
     * Solicita as permissões
     */
    public static boolean validate(Activity activity, int requestCode,String ...permissions) {
        List<String> list = new ArrayList<String>();
        for (String permission: permissions) {
            if (! checkPermission(activity,permission)) {
                list.add(permission);
            }
        }
        if(list.isEmpty()) {
            return true;
        }

        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);

        ActivityCompat.requestPermissions(activity, newPermissions, 1);

        return false;
    }


}
