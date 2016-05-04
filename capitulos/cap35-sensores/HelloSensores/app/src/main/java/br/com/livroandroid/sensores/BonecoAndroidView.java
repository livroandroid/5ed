package br.com.livroandroid.sensores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Usuário on 07/04/2015.
 */

public class BonecoAndroidView extends View {
    private Paint paint = new Paint();
    private Drawable drawable;
    private int dx, dy;

    public BonecoAndroidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BonecoAndroidView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        // Configura o fundo cinza e cria a imagem
        drawable = context.getResources().getDrawable(R.drawable.android);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.LTGRAY);
        // Desenha o fundo da view (um quadrado cinza)
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setTextSize(toPixels(16));
        paint.setColor(Color.BLACK);
        canvas.drawText("x/y:" + dx + "/" + dy, 50, 50, paint);
        // Desenha a imagem das posições x e y
        canvas.translate(dx, dy);
        drawable.draw(canvas);
    }

    private float toPixels(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        float px = dp * scale + 0.5f;
        return px;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
