package br.com.livroandroid.hellografico;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Ricardo Lecheta on 07/03/2015.
 */
public class RectViewDimen extends View {

    public RectViewDimen(Context context) {
        super(context);
    }

    public RectViewDimen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectViewDimen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        Log.d("livro","w:/h " + getWidth() + "/" + getHeight());
        canvas.drawRect(0, 0, getDimen(R.dimen.quadrado_width), getDimen(R.dimen.quadrado_height), paint);
        Log.d("livro","teste");
    }

    // Converte o valor de dp para pixels
    public float getDimen(int resDimen) {
        Resources res = getResources();
        float d = res.getDimension(resDimen);
        return d;
    }

}
