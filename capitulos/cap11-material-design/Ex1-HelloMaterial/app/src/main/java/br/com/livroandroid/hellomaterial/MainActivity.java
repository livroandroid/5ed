package br.com.livroandroid.hellomaterial;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle icicle) {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(icicle);

        String[] items = new String[]{
                "Elevation",
                "Ripple",
                "StateListAnimator",
                "Floating Action Button + Snackbar",
                "CoordinatorLayout",
                "CardView",
                "RecyclerView",
                "RecyclerView Add/Remove",
                "Reveal Effect",
                "Pallete",
                "Activity Transition",
        };

        ListView listView = new ListView(this);
        setContentView(listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    show(new Intent(this, ExemploElevationActivity.class));
                    break;
                case 1:
                    show(new Intent(this, ExemploRippleActivity.class));
                    break;
                case 2:
                    show(new Intent(this, ExemploStateListAnimatorActivity.class));
                    break;
                case 3:
                    show(new Intent(this, ExemploFloatingButtonActivity.class));
                    break;
                case 4:
                    show(new Intent(this, ExemploCoordinatorLayoutActivity.class));
                    break;
                case 5:
                    show(new Intent(this, ExemploCardView.class));
                    break;
                case 6:
                    show(new Intent(this, ExemploRecyclerViewActivity.class));
                    break;
                case 7:
                    show(new Intent(this, ExemploRecyclerViewAddRemoveActivity.class));
                    break;
                case 8:
                    show(new Intent(this, ExemploRevealEffectActivity.class));
                    break;
                case 9:
                    show(new Intent(this, ExemploPaletteActivity.class));
                    break;
                case 10:
                    show(new Intent(this, ExemploActivityTransition.class));
                    break;

                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void show(Intent intent) {
        /**
         * Classe compat para compatibilidade
         */
        Bundle opts = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        ActivityCompat.startActivity(this,intent,opts );
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("livroandroid","Mem Total: " + (Runtime.getRuntime().totalMemory() / 1024) + " mb");
        Log.d("livroandroid","Mem Free: " + (Runtime.getRuntime().freeMemory() / 1024) + " mb");
    }
}