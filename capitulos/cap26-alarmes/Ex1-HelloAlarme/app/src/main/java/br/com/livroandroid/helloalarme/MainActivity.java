package br.com.livroandroid.helloalarme;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Data/Tempo para agendar o alarme
    public long getTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 5);
        long time = c.getTimeInMillis();
        return time;
    }

    public void onClickAgendar(View view) {
        Intent intent = new Intent(LembremeDeComerReceiver.ACTION);
        // Agenda para daqui a 5 seg
        AlarmUtil.schedule(this, intent, getTime());
        //sendBroadcast(intent);
        Toast.makeText(this,"Alarme agendado.",Toast.LENGTH_SHORT).show();
    }

    public void onClickAgendarComRepeat(View view) {
        Intent intent = new Intent(LembremeDeComerReceiver.ACTION);
        // Agenda para daqui a 5 seg, repete a cada 30 seg
        AlarmUtil.scheduleRepeat(this, intent, getTime(), 30 * 1000);
        Toast.makeText(this,"Alarme agendado com repetir.",Toast.LENGTH_SHORT).show();
    }

    public void onClickCancelar(View view) {
        Intent intent = new Intent(LembremeDeComerReceiver.ACTION);
        AlarmUtil.cancel(this,intent);
        Toast.makeText(this,"Alarme cancelado",Toast.LENGTH_SHORT).show();
    }
}
