package br.com.livroandroid.sensores;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Verifica se fez shake no device, isto é, se houve uma mudança brusca de aceleração
 *
 * @author Ricardo Lecheta
 */
public class ShakeActivity extends AcelerometroActivity implements
        SensorEventListener {

    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    @Override
    public void onSensorChanged(SensorEvent se) {
//		super.onSensorChanged(se);

        float x = se.values[0];
        float y = se.values[1];
        float z = se.values[2];

        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));

        float delta = mAccelCurrent - mAccelLast;

        // low-cut filter
        mAccel = mAccel * 0.9f + delta;

        TextView tMsg = (TextView) findViewById(R.id.tMsg);
        if (mAccel > 5) {
            tMsg.setText("Shake " + mAccel);
            Toast.makeText(this, "Shake " + mAccel, Toast.LENGTH_SHORT).show();
        } else {
            tMsg.setText("");
        }
    }
}
