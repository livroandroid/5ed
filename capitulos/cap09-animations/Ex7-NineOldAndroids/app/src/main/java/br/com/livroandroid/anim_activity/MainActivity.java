package br.com.livroandroid.anim_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

/**
 * NineOldAndroids (http://nineoldandroids.com/).
 */
public class MainActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
    }

    public void onClickAnimar(View view) {
        animate(img).xBy(200).yBy(200).rotation(180).alpha(0.5F).setDuration(2000);
    }
}