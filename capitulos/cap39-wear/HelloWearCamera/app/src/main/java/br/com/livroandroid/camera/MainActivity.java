package br.com.livroandroid.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.wearable.Asset;

import java.io.File;

import livroandroid.lib.activity.BaseActivity;
import livroandroid.lib.utils.ImageResizeUtils;
import livroandroid.lib.utils.SDCardUtils;
import livroandroid.lib.wear.WearUtil;


public class MainActivity extends BaseActivity {
    // Caminho para salvar o arquivo
    private File file;
    private ImageView imgView;
    private WearUtil wearUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wearUtil = new WearUtil(this);

        imgView = (ImageView) findViewById(R.id.imagem);

        // Cria o caminho do arquivo no sdcard
        // /storage/sdcard/Android/data/br.com.livroandroid.multimidia/files/Pictures/foto.jpg
        file = SDCardUtils.getPrivateFile(getBaseContext(), "foto.jpg", Environment.DIRECTORY_PICTURES);

        findViewById(R.id.btAbrirCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama a intent informando o arquivo para salvar a foto
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(i, 0);
            }
        });

        findViewById(R.id.btEnviarFoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Valida se o arquivo existe
                if (file != null && file.exists()) {
                    // Manda a foto reduzida com 100x100 pixels
                    int w = 200;
                    int h = 200;
                    Bitmap bitmap = ImageResizeUtils.getResizedImage(Uri.fromFile(file), w, h, false);

                    // Converte o bitmap para Asset
                    Asset asset = wearUtil.getAssetFromBitmap(bitmap);

                    // Envia o asset para o relogio
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("foto", asset);
                    wearUtil.putData("/foto", bundle);
                }
            }
        });

        if (savedInstanceState != null) {
            // Se girou a tela recupera o estado
            file = (File) savedInstanceState.getSerializable("file");
            showImage(file);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar o estado caso gire a tela
        outState.putSerializable("file", file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && file != null) {
            showImage(file);
        }
    }

    // Atualiza a imagem na tela
    private void showImage(File file) {
        if (file != null && file.exists()) {
            Log.d("foto", file.getAbsolutePath());

            int w = imgView.getWidth();
            int h = imgView.getHeight();
            Bitmap bitmap = ImageResizeUtils.getResizedImage(Uri.fromFile(file), w, h, false);
            Toast.makeText(this, "w/h:" + imgView.getWidth() + "/" + imgView.getHeight() + " > " + "w/h:" + bitmap.getWidth() + "/" + bitmap.getHeight(), Toast.LENGTH_SHORT).show();

            imgView.setImageBitmap(bitmap);
        }
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