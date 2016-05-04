package br.com.livroandroid.gestos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class SwipeActivity extends ActionBarActivity {
    TextView text;
    private ImageView img;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        img = (ImageView) findViewById(R.id.img);
        text = (TextView) findViewById(R.id.text);
        text.setText("Faça um gesto");
        // Cria a instância de GestureDetector e informa o listener
        gestureDetector = new GestureDetector(this,new MyFlingGestureDetector());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Aqui está o segredo. Delega o tratamento do touch para a classe GestureDetector
        boolean tratouEvento = gestureDetector.onTouchEvent(event);
        if(tratouEvento) {
            return tratouEvento;
        }
        return super.onTouchEvent(event);
    }
    // Listener para reconhecer o gesto
    class MyFlingGestureDetector extends GestureDetector.SimpleOnGestureListener {
        // Distância do movimento em pixels. Por exemplo, o usuário tocou na tela e moveu o
        // dedo 100 pixels para a direita
        private float swipeMinDistance = 50;
        // Velocidade do moviment em pixels por segundo
        private float swipeThreasholdVelocity = 200;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY)  {
            // Usuário fez um gesto de swipe lateral
            try {
                if (e1.getX() - e2.getX() > swipeMinDistance && Math.abs(velocityX) >
                        swipeThreasholdVelocity) {
                    // Fez um gesto da direita para a esquerda
                    text.setText("<<< Left");
                    animate(img).xBy(-100);
                } else if (e2.getX() - e1.getX() > swipeMinDistance && Math.abs(velocityX) >
                        swipeThreasholdVelocity) {
                    // Fez um gesto da esquerda para a direita
                    text.setText("Right >>>");
                    animate(img).xBy(100);
                }
            } catch (Exception e) { }
            return false;
        }
    }
}

