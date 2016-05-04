package br.com.livroandroid.helloviews.ex7;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import br.com.livroandroid.helloviews.R;

public class FullScreenActivity extends Activity {
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        final DismissOverlayView  d = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        d.setIntroText("Para sair, clique e segure.");
        d.showIntroIfNecessary();

        // Adiciona o detector de gestos
        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent ev) {
                d.show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mDetector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }
}
