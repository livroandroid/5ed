package br.com.livroandroid.helloalarme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAgendar(View view) {
        int id = 1;
        JobUtil.schedule(this, HelloJobService.class, id);

        Toast.makeText(this, "Job agendado", Toast.LENGTH_SHORT).show();
    }

    public void onClickCancelar(View view) {
        JobUtil.cancel(this, 1);

        Toast.makeText(this, "Job cancelado", Toast.LENGTH_SHORT).show();
    }
}
