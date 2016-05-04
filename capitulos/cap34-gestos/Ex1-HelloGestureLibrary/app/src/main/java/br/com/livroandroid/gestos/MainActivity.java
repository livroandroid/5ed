package br.com.livroandroid.gestos;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Exemplo de gestos
 *
 * @author rlecheta
 */

public class MainActivity extends ActionBarActivity implements OnGesturePerformedListener {
    private GestureLibrary gestureLib;
    private TextView text;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        img = (ImageView) findViewById(R.id.img);

        // Configura o listener do GestureOverlayView
        GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureView);
        gestureOverlayView.addOnGesturePerformedListener(this);

        // res/raw
        // gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Lê o arquivo de gestos do sdcard
        readGestures();
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        if(gestureLib == null) return;
        // Faz a biblioteca de gestos reconhecer o movimento
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
        Prediction maxScore = null;
        for (Prediction prediction : predictions) {
            // Vamos aceitar somente escores maiores que cinco
            if (prediction.score > 5.0) {
                if (maxScore == null || maxScore.score < prediction.score) {
                    maxScore = prediction;
                }
            }
        }
        // Se encontrou algum gesto com escore alto, vamos mostrar o texto
        if (maxScore != null) {
            // Se o score é maior que 5
            String desc = maxScore.name + ", score: " + maxScore.score;
            text.setText(desc);

            // Mostra o gesto em um ImageView
            int w = (int) gesture.getBoundingBox().width();
            int h = (int) gesture.getBoundingBox().height();
            final Bitmap b = gesture.toBitmap(w, h, 8, Color.GREEN);
            img.setImageBitmap(b);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(this, SalvarGestoActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void readGestures() {
        File file = new File(Environment.getExternalStorageDirectory(), "gestures");
        if(file.exists()) {
            gestureLib = GestureLibraries.fromFile(file);
        }
        if (gestureLib != null && gestureLib.load()) {
            Toast.makeText(this,"Biblioteca de gestos lida com sucesso.",Toast.LENGTH_SHORT).show();
        }
    }
}
