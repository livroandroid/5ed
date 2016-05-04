package br.com.livroandroid.sensores;

import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Demonstra como mover uma view com o acelerômetro.
 */
public class AcelerometroJogoActivity extends AcelerometroActivity {
    // Posições para desenhar a imagem
    private BonecoAndroidView androidView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro_jogo);
        androidView = (BonecoAndroidView) findViewById(R.id.bonecoView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        super.onSensorChanged(event);
        // Lê os valores retornados pelo acelerômetro
        float values[] = SensorUtil.fixAcelerometro(this, event);
        float sensorX = values[0];
        float sensorY = values[1];
        // Vai incrementando os valores de x e y para o objeto se mover
        int newdx = androidView.getDx() + (int) sensorX * 10;
        int newdy = androidView.getDy() + (int) sensorY * 10;
        int imgW = androidView.getDrawable().getIntrinsicWidth();
        int imgH = androidView.getDrawable().getIntrinsicHeight();
        // Não deixa o valor ficar negativo ou maior que o tamanho da tela
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (!(newdx < 0 || newdx + imgW > displayMetrics.widthPixels)) {
            // Atualiza o eixo x
            androidView.setDx(newdx);
        }
        int actionBarH = displayMetrics.heightPixels - androidView.getHeight();
        if (!(newdy < 0 || newdy + imgH > displayMetrics.heightPixels - actionBarH)) {
            // Atualiza o eixo y
            androidView.setDy(newdy);
        }
        // Redesenha a view
        androidView.invalidate();
    }
}
