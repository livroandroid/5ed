package br.com.livroandroid.helloviews.ex4;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.livroandroid.helloviews.R;
import br.com.livroandroid.shared.Carro;

/**
 * Created by ricardo on 19/04/15.
 */
public class CarroAdapter extends WearableListView.Adapter {
    private List<Carro> carros;
    private final LayoutInflater mInflater;

    public CarroAdapter(Context context, List<Carro> carros) {
        mInflater = LayoutInflater.from(context);
        this.carros = carros;
    }

    // Provide a reference to the type of views you're using
    public static class ItemViewHolder extends WearableListView.ViewHolder {
        private TextView textView;
        private ImageView img;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tNome);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        return new ItemViewHolder(mInflater.inflate(R.layout.adapter_carro, parent,false));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder,
                                 int position) {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        Carro c = carros.get(position);
        itemHolder.textView.setText(c.nome);
        itemHolder.img.setImageResource(c.img);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return carros != null ? carros.size() : 0;
    }
}