package br.com.livroandroid.helloservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import livroandroid.lib.utils.NotificationUtil;

public class MainActivity extends ActionBarActivity {

    public static final Class<? extends Service> CLS = HelloIntentService.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View view) {
        startService(new Intent(this, CLS));
    }

    public void onClickStop(View view) {
        stopService(new Intent(this, CLS));
        NotificationUtil.cancell(this, 1);
    }
}
