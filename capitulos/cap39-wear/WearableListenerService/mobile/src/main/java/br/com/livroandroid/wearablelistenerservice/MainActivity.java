package br.com.livroandroid.wearablelistenerservice;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import livroandroid.lib.wear.WearUtil;

public class MainActivity extends ActionBarActivity {

    private WearUtil wearUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wearUtil = new WearUtil(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wearUtil.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wearUtil.disconnect();
    }

    public void onClickNotification(View view) {
        wearUtil.sendMessage("/notificacao",new byte[]{1});
        Log.d("wear","sendMessage /notificacao");
    }
}
