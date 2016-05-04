package br.com.livroandroid.hellodesignsupportlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by ricardo on 12/06/15.
 */
class PlanetaFragment extends Fragment {
    PlanetaAdapter adapter;
    private RecyclerView recyclerView;
    private List<Planeta> planetas;

    public PlanetaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.planeta_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setHasFixedSize(true);

        planetas = Planeta.getPlanetas();
        recyclerView.setAdapter(adapter = new PlanetaAdapter(getActivity(), planetas, onClickPlaneta()));

        return view;
    }

    protected PlanetaAdapter.PlanetaOnClickListener onClickPlaneta() {
        final Intent intent = new Intent(getActivity(), PlanetaActivity.class);

        return new PlanetaAdapter.PlanetaOnClickListener() {
            @Override
            public void onClickPlaneta(PlanetaAdapter.PlanetasViewHolder holder, int idx) {
                Planeta p = planetas.get(idx);

                ImageView img = holder.img;
                intent.putExtra("imgPlaneta", p.img);
                String key = getString(R.string.transition_key);

                // Compat
                ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), img, key);
                ActivityCompat.startActivity(getActivity(), intent, opts.toBundle());
            }
        };
    }
}