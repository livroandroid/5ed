package br.com.livroandroid.camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMapItem;

import livroandroid.lib.wear.WearUtil;

public class MainWearActivity extends Activity implements DataApi.DataListener {

    private WearUtil wearUtil;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wearUtil = new WearUtil(this);
        wearUtil.setDataListener(this);

        img = (ImageView) findViewById(R.id.img);
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

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent event : dataEvents) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/foto") == 0) {
                    DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                    Asset photo = dataMapItem.getDataMap().getAsset("foto");
                    final Bitmap bitmap = wearUtil.getBitmapFromAsset(photo);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Recebe a foto
                            img.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        }
    }
}
