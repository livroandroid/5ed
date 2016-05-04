package br.com.livroandroid.hellowear;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.wearable.Asset;

import br.com.livroandroid.shared.WearUtil;
import livroandroid.lib.activity.BaseActivity;
import livroandroid.lib.utils.ImageResizeUtils;

public class MainMobileActivity extends BaseActivity {

    private WearUtil wearUtil;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);

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

    public void onClickSendMessage(View view) {
        count++;
        wearUtil.sendMessage("/msg", new byte[]{(byte) count});
    }

    public void onClickPutData(View view) {
        count++;
        Bundle b = new Bundle();
        b.putString("msg", "Olá Data API");
        b.putInt("count", count);
        // Cria o Asset
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ferrari_ff);
        Asset asset = wearUtil.getAssetFromBitmap(bitmap);
        b.putParcelable("foto", asset);
        // Compartilha os dados com o wearable
        wearUtil.putData("/msg", b);
    }
}
