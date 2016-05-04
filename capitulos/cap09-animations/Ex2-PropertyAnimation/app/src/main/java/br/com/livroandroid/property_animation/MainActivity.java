package br.com.livroandroid.property_animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String[] ops = new String[]{
            "Alpha - ValueAnimator",
            "Alpha - ObjectAnimator",
            "Rotate - ObjectAnimator",
            "Scale - ObjectAnimator",
            "Translate - ObjectAnimator",
            "AnimatorSet",
            "ViewPropertyAnimator",
            "AnimatorListener",
            "TextView - ValueAnimator",
            "Sair"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        ListView listView = new ListView(this);
        setContentView(listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ops));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, ExemploValueAnimator.class));
                break;
            case 1:
                startActivity(new Intent(this, AlphaAnim.class));
                break;
            case 2:
                startActivity(new Intent(this, RotateAnim.class));
                break;
            case 3:
                startActivity(new Intent(this, ScaleAnim.class));
                break;
            case 4:
                startActivity(new Intent(this, TranslateAnim.class));
                break;
            case 5:
                startActivity(new Intent(this, ExemploAnimatorSet.class));
                break;
            case 6:
                startActivity(new Intent(this, ExemploViewPropertyAnimatorActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, ExemploAnimatorListenerApagarTela.class));
                break;
            case 8:
                startActivity(new Intent(this, ExemploValueAnimatorTextViewActivity.class));
                break;
            default:
                finish();
        }
    }
}
