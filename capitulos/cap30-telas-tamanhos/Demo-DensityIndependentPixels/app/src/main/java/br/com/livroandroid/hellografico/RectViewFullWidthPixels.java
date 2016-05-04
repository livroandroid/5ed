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
public class RectViewFullWidthPixels extends View {

    public RectViewFullWidthPixels(Context context) {
        super(context);
    }

    public RectViewFullWidthPixels(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectViewFullWidthPixels(Context context, AttributeSet attrs, int defStyleAttr) {
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
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }
}
