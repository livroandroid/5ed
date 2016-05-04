package br.com.livroandroid.cap07_view.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * @author Ricardo Lecheta
 */
public class TouchScreenViewActivity extends Activity {
    private static final String CATEGORIA = "livro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new TouchScreenView(this));

        Toast.makeText(this, "Mova o objeto com o touch", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(CATEGORIA, "TouchScreenViewActivity.onTouchEvent");
        return super.onTouchEvent(event);
    }
}
