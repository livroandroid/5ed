package br.com.livroandroid.cap07_view.canvas;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Ricardo Lecheta on 21/12/2014.
 */
public class ExemploMinhaViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new MinhaView(this));
    }
}
