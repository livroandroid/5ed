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
 * Adaptador customizado que exibe o layout definido em smile_row.xml
 * <p/>
 * As imagens são exibidas utilizando um ImageView
 *
 * @author ricardo
 */
public class SmileAdapter extends BaseAdapter {
    protected static final String TAG = "livro";
    private Context context;
    private List<Smile> lista;

    public SmileAdapter(Context context, List<Smile> lista) {
        this.context = context;
        this.lista = lista;
    }

    public int getCount() {
        return lista.size();
    }

    public Object getItem(int posicao) {
        Smile smile = lista.get(posicao);
        return smile;
    }

    public long getItemId(int posicao) {
        return posicao;
    }

    public View getView(int posicao, View convertView, ViewGroup parent) {
        // Recupera o Smile da posi��o atual
        Smile smile = lista.get(posicao);

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.adapter_smile, parent, false);

        // Atualiza o valor do Text para o nome do Smile
        TextView textNome = (TextView) v.findViewById(R.id.nome);
        textNome.setText(smile.nome);

        // Atualiza a imagem para a imagem do Smile
        ImageView img = (ImageView) v.findViewById(R.id.img);
        img.setImageResource(smile.getImagem());

        // Retorna a view
        return v;
    }
}
