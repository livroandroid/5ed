package br.com.livroandroid.cap07_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ricardo on 27/12/14.
 */
public class PlanetaAdapter extends BaseAdapter {
    private final Context context;
    private final List<Planeta> planetas;

    public PlanetaAdapter(Context context, List<Planeta> planetas) {

        this.context = context;
        this.planetas = planetas;
    }

    @Override
    public int getCount() {

        return planetas != null ? planetas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return planetas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla a view
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_planeta, parent, false);
        // Faz findViewById das views que precisa atualizar
        TextView t = (TextView) view.findViewById(R.id.tNomePlaneta);
        ImageView img = (ImageView) view.findViewById(R.id.imgPlaneta);
        // Atualiza os valores das views
        Planeta planeta = planetas.get(position);
        t.setText(planeta.nome);
        img.setImageResource(planeta.img);
        // Retorna a view deste planeta
        return view;
    }
}
