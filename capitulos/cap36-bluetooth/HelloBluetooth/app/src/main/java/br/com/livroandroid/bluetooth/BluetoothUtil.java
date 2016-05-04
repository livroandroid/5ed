package br.com.livroandroid.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Usuário on 11/04/2015.
 */
public class BluetoothUtil {
    public static void makeVisible(Context context, int durationSeconds) {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, durationSeconds);
        context.startActivity(discoverableIntent);
    }
}
