package br.com.livroandroid.wearablelistenerservice;

import android.util.Log;

import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;

import livroandroid.lib.utils.NotificationUtil;

/**
 * Created by ricardo on 21/04/15.
 */
public class HelloListenerService extends WearableListenerService {
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        String path = messageEvent.getPath();
        Log.d("wear","onMessageReceived: " + path);
        if("/notificacao".equals(path)) {
            NotificationUtil.create(this,R.mipmap.ic_launcher,"Olá","Notificação disparada pelo WearableListenerService!");
        }
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        super.onDataChanged(dataEvents);
    }

    @Override
    public void onPeerConnected(Node peer) {
        super.onPeerConnected(peer);
    }

    @Override
    public void onPeerDisconnected(Node peer) {
        super.onPeerDisconnected(peer);
    }
}
