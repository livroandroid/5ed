package br.com.livroandroid.cap07_view.canvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MinhaView extends View {
    // Para definir a cor RGB
    private Paint pincelVermelho;
    private Paint pincelPreto;
    private Paint pincelAzul;

    public MinhaView(Context context) {
        this(context, null);
    }

    public MinhaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.LTGRAY);
        // Vermelho
        pincelVermelho = new Paint();
        pincelVermelho.setARGB(255, 255, 0, 0);
        // Preto
        pincelPreto = new Paint();
        pincelPreto.setARGB(255, 0, 0, 0);
        // Azul
        pincelAzul = new Paint();
        pincelAzul.setARGB(255, 0, 0, 255);
        // Configura a View para receber foco e tratar eventos de teclado
        setFocusable(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Desenha um quadrado
        canvas.drawRect(toPixels(getContext(), 20), toPixels(getContext(), 20), toPixels(getContext(), 100), toPixels(getContext(), 100), pincelAzul);
        // Desenha uma linha
        canvas.drawLine(toPixels(getContext(), 100), toPixels(getContext(), 100), toPixels(getContext(), 200), toPixels(getContext(), 200), pincelPreto);
        // Desenha um circulo
        canvas.drawCircle(toPixels(getContext(), 200), toPixels(getContext(), 200), toPixels(getContext(), 50), pincelVermelho);
    }

    // Converte um valor em dp para pixels
    public float toPixels(Context context, float dip) {
        Resources r = context.getResources();
        float densidade = r.getDisplayMetrics().density; // Densidade da tela
        int px = (int) (dip * densidade + 0.5f);
        return dip;
    }

}
