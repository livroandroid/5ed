package br.com.livroandroid.wearnotification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import livroandroid.lib.activity.BaseActivity;
import livroandroid.lib.utils.NotificationUtil;
import livroandroid.lib.wear.WearUtil;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "wear";
    private WearUtil wearUtil;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        wearUtil = new WearUtil(this);

        String[] items = new String[]{
                "Pages Notification",
                "Stacking Notifications",
                "Remote Input"
        };

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            String item = parent.getAdapter().getItem(position).toString();
            if("Pages Notification".equals(item)) {
                NotificationWearUtil.createPagesNotification(this);
            } else if("Stacking Notifications".equals(item)) {
                createStacksNotification();
            } else if("Remote Input".equals(item)) {
                Intent intent = new Intent(this, ReplyActivity.class);
                NotificationWearUtil.createRemoteInputNotification(this, intent);
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void createStacksNotification() {
        NotificationUtil.createStackNotification(this,1,"grupoId",null,R.mipmap.ic_launcher, "Título 1", "Mensagem 1");
        NotificationUtil.createStackNotification(this,2,"grupoId",null,R.mipmap.ic_launcher,"Título 2","Mensagem 2");
        NotificationUtil.createStackNotification(this,3,"grupoId",null,R.mipmap.ic_launcher,"Título 3","Mensagem 3");
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
}
