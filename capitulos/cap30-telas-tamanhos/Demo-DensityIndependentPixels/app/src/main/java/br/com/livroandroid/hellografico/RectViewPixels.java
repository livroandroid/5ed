package br.com.livroandroid.hellografico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ricardo Lecheta on 07/03/2015.
 */
public class RectViewPixels extends View {

    public RectViewPixels(Context context) {
        super(context);
    }

    public RectViewPixels(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectViewPixels(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        // Quadrado somente com a borda para mostrar o tamanho total
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight()-1, paint);

        // Quadrado preenchendo incorretamente (com pixels)
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, 100, 100, paint);
    }
}
