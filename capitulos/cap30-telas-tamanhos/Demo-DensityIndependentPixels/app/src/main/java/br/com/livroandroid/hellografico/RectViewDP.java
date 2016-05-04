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
public class RectViewDP extends View {

    public RectViewDP(Context context) {
        super(context);
    }

    public RectViewDP(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectViewDP(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        Log.d("livro","w:/h " + getWidth() + "/" + getHeight());
        canvas.drawRect(0, 0, toPixels(100), toPixels(100), paint);
        Log.d("livro","teste");
    }

    // Converte o valor de dp para pixels
    public float toPixels(float dip) {
        Resources r = getResources();
        float densidade = r.getDisplayMetrics().density; // Densidade da tela
        int px = (int) (dip * densidade + 0.5f);
        Log.d("livro","Densidade: " + densidade);
        return px;
    }

}
