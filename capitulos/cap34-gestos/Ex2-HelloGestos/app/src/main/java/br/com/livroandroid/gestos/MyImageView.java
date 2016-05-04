package br.com.livroandroid.gestos;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
public class MyImageView extends View {
    private static final int img = R.drawable.android;
    private Drawable drawable;
    private float scaleFactor = 1;
    private int imgW;
    private int imgH;
    private Paint paint;

    public MyImageView(Context context) {
        super(context);
        init(context);
    }
    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        // Cria o drawable/imagem para desenhar
        drawable = context.getResources().getDrawable(img);
        imgW = drawable.getIntrinsicWidth();
        imgH = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, imgW, imgH);
        // Cria o pincel azul
        paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.BLUE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Desenha um quadrado apenas para visualizar a view
        canvas.drawRect(0, 0, getWidth() - 1, getHeight() - 1, paint);
        // Multiplica a largura e a altura pela escala
        int larguraComEscala = (int) (imgW * scaleFactor);
        int alturaComEscala = (int) (imgH * scaleFactor);
        // Posiciona o desenho no centro da tela
        int dx = getWidth() / 2 - larguraComEscala / 2;
        int dy = getHeight() / 2 - alturaComEscala / 2;
        canvas.translate(dx, dy);
        // Faz a escala do desenho (para dar zoom out/in)
        canvas.scale(scaleFactor, scaleFactor);
        // Desenha a imagem
        drawable.draw(canvas);
    }

    public void setScaleFactor(float scaleFactor) {
        // Permite à activity configurar a escala e redesenhar a view
        this.scaleFactor = scaleFactor;
    }
}
