package br.com.livroandroid.hellomaterial;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ExemploActivityTransition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exemplo_activity_transition);

        findViewById(R.id.btOk1).setOnClickListener(onClickOk1());
        findViewById(R.id.btOk2).setOnClickListener(onClickOk2());
        findViewById(R.id.btOk3).setOnClickListener(onClickOk3());
        findViewById(R.id.btOk4).setOnClickListener(onClickOk4());
        findViewById(R.id.btOk5).setOnClickListener(onClickOk5());
        findViewById(R.id.btOk6).setOnClickListener(onClickOk6());
    }

    private View.OnClickListener onClickOk1() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickOk2() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);
                ActivityOptions opts = ActivityOptions.makeSceneTransitionAnimation(getActivity());
                startActivity(intent, opts.toBundle());
            }
        };
    }

    private View.OnClickListener onClickOk3() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);
                int animEntrada = R.anim.fade_in;
                int animSaida = R.anim.fade_out;
                ActivityOptions opts = ActivityOptions.makeCustomAnimation(getActivity(), animEntrada, animSaida);
                startActivity(intent, opts.toBundle());
            }
        };
    }

    private View.OnClickListener onClickOk4() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);
                ActivityOptions opts = ActivityOptions.makeCustomAnimation(getActivity(),
                        R.anim.zoom_enter, R.anim.zoom_enter);
                startActivity(intent, opts.toBundle());
            }
        };
    }

    private View.OnClickListener onClickOk5() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);
                ActivityOptions opts = ActivityOptions.makeScaleUpAnimation(
                        v, 0, 0, v.getWidth(), v.getHeight());
                startActivity(intent, opts.toBundle());
            }
        };
    }

    private View.OnClickListener onClickOk6() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img = (ImageView) findViewById(R.id.img);
                Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);
                String key = getString(R.string.transition_key);
                ActivityOptions opts = ActivityOptions.makeSceneTransitionAnimation(getActivity(), img, key);
                startActivity(intent, opts.toBundle());

            }
        };
    }

    private AppCompatActivity getActivity() {
        return this;
    }


}
