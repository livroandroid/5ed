package br.com.livroandroid.gestos;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TextView;


public class ZoomActivity extends ActionBarActivity {
    TextView text;
    private ScaleGestureDetector gestureDetector;
    private MyImageView img;
    private float scaleFactor = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        // Informa o fator de escala-padrão
        img = (MyImageView) findViewById(R.id.img);
        img.setScaleFactor(scaleFactor);
        text = (TextView) findViewById(R.id.text);
        text.setText("Faça um gesto de pinch/zoom");
        // Configura o detector de gestos
        gestureDetector = new ScaleGestureDetector(this, new ZoomGestureDetector());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Delega o evento de touch ao detector de gestos
        boolean ok = gestureDetector.onTouchEvent(event);
        if(ok) {
            return super.onTouchEvent(event);
        }
        return ok;
    }
    // Detecta o zoom
    class ZoomGestureDetector extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // Armazena o fator de escala detectado
            scaleFactor *= detector.getScaleFactor();
            // Exibe o fator de escala no TextView
            text.setText(String.valueOf(scaleFactor));
            // Altera o fator de escala da view customizada e pede para ela se redesenhar
            img.setScaleFactor(scaleFactor);
            img.invalidate();
            return true;
        }
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
        }
    }
}

