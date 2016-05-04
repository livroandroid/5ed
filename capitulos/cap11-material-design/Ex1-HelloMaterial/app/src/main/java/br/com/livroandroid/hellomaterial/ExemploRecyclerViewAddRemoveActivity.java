package br.com.livroandroid.hellomaterial;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ExemploRecyclerViewAddRemoveActivity extends ExemploRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        planetas = new ArrayList<Planeta>();
        recyclerView.setAdapter(adapter = new PlanetaAdapter(this, planetas, onClickPlaneta()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exemplo_recycler_view_add_remove, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            // Pega um planeta random e adiciona     na lista
            Planeta p = getRandomPlaneta();
            planetas.add(0,p);
            adapter.notifyItemInserted(0);

            return true;
        } else if (id == R.id.action_remove) {
            if(! planetas.isEmpty()) {
                planetas.remove(0);
                adapter.notifyItemRemoved(0);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private Planeta getRandomPlaneta() {
        List<Planeta> list = Planeta.getPlanetas();
        int idx = new Random().nextInt(list.size()-1);
        Planeta p = list.get(idx);
        return p;
    }


}
