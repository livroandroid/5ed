package br.com.livroandroid.hellomaterial;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ExemploRecyclerViewActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected List<Planeta> planetas;
    protected PlanetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exemplo_recycler_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setHasFixedSize(true);

        planetas = Planeta.getPlanetas();
        recyclerView.setAdapter(adapter = new PlanetaAdapter(this, planetas, onClickPlaneta()));
    }

    protected PlanetaAdapter.PlanetaOnClickListener onClickPlaneta() {
        final Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);

        return new PlanetaAdapter.PlanetaOnClickListener() {
            @Override
            public void onClickPlaneta(PlanetaAdapter.PlanetasViewHolder holder, int idx) {
                Planeta p = planetas.get(idx);
                //Toast.makeText(getBaseContext(), "Planeta: " + p.nome, Toast.LENGTH_SHORT).show();

                ImageView img = holder.img;
                intent.putExtra("imgPlaneta", p.img);
                String key = getString(R.string.transition_key);

                // Somente Android 5.0
                //ActivityOptions opts = ActivityOptions.makeSceneTransitionAnimation(getActivity(), img, key);
                //ActivityOptions opts = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.shake, R.anim.abc_slide_in_top);
                //startActivity(intent, opts.toBundle());

                ActivityOptions opts = ActivityOptions.makeSceneTransitionAnimation(getActivity(), img, key);
                startActivity(intent, opts.toBundle());

                // Compat
                //ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), img, key);
                //ActivityCompat.startActivity(getActivity(), intent, opts.toBundle());
            }
        };
    }

    private Activity getActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exemplo_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_linear_layout) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            return true;
        } else if (id == R.id.action_grid_layout) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
