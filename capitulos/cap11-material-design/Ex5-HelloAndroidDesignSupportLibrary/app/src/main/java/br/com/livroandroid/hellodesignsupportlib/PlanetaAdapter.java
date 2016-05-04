package br.com.livroandroid.hellodesignsupportlib;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


// Herda de RecyclerView.Adapter e declara o tipo genérico <PlanetaAdapter.PlanetasViewHolder>
public class PlanetaAdapter extends RecyclerView.Adapter<PlanetaAdapter.PlanetasViewHolder> {
    protected static final String TAG = "livroandroid";
    private final List<Planeta> planetas;
    private final Context context;
    private final PlanetaOnClickListener onClickListener;

    public interface PlanetaOnClickListener {
        public void onClickPlaneta(PlanetasViewHolder holder, int idx);
    }

    public PlanetaAdapter(Context context, List<Planeta> planetas, PlanetaOnClickListener onClickListener) {
        this.context = context;
        this.planetas = planetas;
        this.onClickListener = onClickListener;
    }

    @Override
    public PlanetasViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Este método cria uma subclasse de RecyclerView.ViewHolder
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_planeta, viewGroup, false);
        // Cria a classe do ViewHolder
        PlanetasViewHolder holder = new PlanetasViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PlanetasViewHolder holder, final int position) {
        // Este método recebe o índice do elemento, e atualiza as views que estão dentro do ViewHolder
        Planeta c = planetas.get(position);
        // Atualizada os valores nas views
        holder.tNome.setText(c.nome);
        holder.img.setImageResource(c.img);

        // Click
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chama o listener para informar que clicou no Planeta
                    onClickListener.onClickPlaneta(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.planetas != null ? this.planetas.size() : 0;
    }

    // Subclasse de RecyclerView.ViewHolder. Contém todas as views.
    public static class PlanetasViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        ImageView img;
        ProgressBar progress;
        private View view;

        public PlanetasViewHolder(View view) {
            super(view);
            this.view = view;
            // Cria as views para salvar no ViewHolder
            tNome = (TextView) view.findViewById(R.id.tNome);
            img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progress);
        }
    }


}