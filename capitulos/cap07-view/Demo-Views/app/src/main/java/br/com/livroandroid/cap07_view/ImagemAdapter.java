package br.com.livroandroid.cap07_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImagemAdapter extends BaseAdapter {
    private Context ctx;
    private final int[] imagens;

    public ImagemAdapter(Context c, int[] imagens) {
        this.ctx = c;
        this.imagens = imagens;
    }

    @Override
    public int getCount() {
        return imagens != null ? imagens.length : 0;
    }

    @Override
    public Object getItem(int posicao) {
        return posicao;
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        // Infla a view que está no XML
        View view = LayoutInflater.from(this.ctx).inflate(R.layout.adapter_imagem, parent, false);
        // Utiliza o findViewById para obter o ImageView
        ImageView img = (ImageView) view.findViewById(R.id.img);
        // Altera a imagem (baseado na posição do array)
        img.setImageResource(imagens[posicao]);
        // Retorna a view
        return view;
    }
}
