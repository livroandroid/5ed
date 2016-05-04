package br.com.livroandroid.hellodesignsupportlib;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;


public class ExemploRecyclerViewActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected List<Planeta> planetas;
    protected PlanetaAdapter adapter;
    private View fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exemplo_recycler_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Magic :-)
        final int toolbarHeight = Utils.getToolbarHeight(this);
        recyclerView.setPadding(recyclerView.getPaddingLeft(), toolbarHeight,
                recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());

        planetas = Planeta.getPlanetas();
        recyclerView.setAdapter(adapter = new PlanetaAdapter(this, planetas, onClickPlaneta()));

        // FAB
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        fab = findViewById(R.id.btFab);
        fab.startAnimation(animation);

        final int fabMargin = getResources().getDimensionPixelSize(R.dimen.fab_margin);

        // Scroll Up/Down
        recyclerView.addOnScrollListener(new UpDownRecyclerScroll() {
            @Override
            public void show() {
                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                toolbar.animate().alpha(1).setInterpolator(new DecelerateInterpolator(1)).start();
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void hide() {
                toolbar.animate().translationY(-toolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
                toolbar.animate().alpha(0).setInterpolator(new AccelerateInterpolator(1)).start();
                fab.animate().translationY(fab.getHeight() + fabMargin).setInterpolator(new AccelerateInterpolator(2)).start();
            }
        });
    }

    protected PlanetaAdapter.PlanetaOnClickListener onClickPlaneta() {
        final Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);

        return new PlanetaAdapter.PlanetaOnClickListener() {
            @Override
            public void onClickPlaneta(PlanetaAdapter.PlanetasViewHolder holder, int idx) {
                Planeta p = planetas.get(idx);

                ImageView img = holder.img;
                intent.putExtra("imgPlaneta", p.img);
                String key = getString(R.string.transition_key);

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

    public void onClickFab(View view) {
        Snackbar
                .make(recyclerView, "Clicou no FAB mini.", Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "OK!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
