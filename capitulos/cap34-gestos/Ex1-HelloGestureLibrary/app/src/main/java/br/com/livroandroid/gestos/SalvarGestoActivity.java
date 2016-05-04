package br.com.livroandroid.gestos;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class SalvarGestoActivity extends ActionBarActivity implements GestureOverlayView.OnGesturePerformedListener {

    private TextView text;
    private GestureLibrary gestureLib;

    private final File file = new File(Environment.getExternalStorageDirectory(), "gestures");
    private ImageView img;
    private Gesture gesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_gesto);

        text = (TextView) findViewById(R.id.text);
        img = (ImageView) findViewById(R.id.img);

        GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureView);
        gestureOverlayView.addOnGesturePerformedListener(this);

        // Carrega a biblioteca de gestos, a partir do arquivo salvo em /res/layout/gestures
        gestureLib = GestureLibraries.fromFile(file);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        // Mostra o gesto em um ImageView
        int w = (int) gesture.getBoundingBox().width();
        int h = (int) gesture.getBoundingBox().height();
        final Bitmap b = gesture.toBitmap(w, h, 8, Color.GREEN);
        img.setImageBitmap(b);

        overlay.setGesture(gesture);

        this.gesture = gesture;
    }

    public void onClickSalvar(View view) {
        String nome = text.getText().toString();

        if(gesture != null && nome != null) {
            // Salva o gesto
            gestureLib.addGesture(nome, gesture);
            gestureLib.save();

            final String path = file.getAbsolutePath();
            Toast.makeText(this, "Gesto salvo: " + nome + ", path: " + path, Toast.LENGTH_SHORT).show();
            Log.d("livro", "Gesto salvo: " + nome + ", path: " + path);
        }
    }
}
