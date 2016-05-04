package br.com.livroandroid.sensores;

import android.content.Context;
import android.hardware.SensorEvent;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by Usuário on 07/04/2015.
 */
public class SensorUtil {
    public static float[] fixAcelerometro(Context context, SensorEvent event) {
        float sensorX = 0;
        float sensorY = 0;
        float sensorZ = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // Verifica a rotação do aparelho
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                // Vertical
                sensorX = -event.values[0];
                sensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                // Horizontal (botões para a direita)
                sensorX = event.values[1];
                sensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                // Vertical: de ponta-cabeça
                sensorX = -event.values[0];
                sensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                // Horizontal (botões para a esquerda)
                sensorX = -event.values[1];
                sensorY = -event.values[0];
                break;
        }
        // Troca os valores de x e y
        float[] values = new float[3];
        values[0] = sensorX;
        values[1] = sensorY;
        values[2] = sensorZ;
        return values;
    }

    public static String getRotationString(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // Verifica a rotação do aparelho
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                return "ROTATION_0";
            case Surface.ROTATION_90:
                return "ROTATION_90";
            case Surface.ROTATION_180:
                return "ROTATION_180";
            case Surface.ROTATION_270:
                return "ROTATION_270";
        }
        return null;
    }
}
