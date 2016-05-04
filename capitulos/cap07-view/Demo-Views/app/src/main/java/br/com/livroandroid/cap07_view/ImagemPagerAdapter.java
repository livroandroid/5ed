package br.com.livroandroid.cap07_view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagemPagerAdapter extends PagerAdapter {
    private Context ctx;
    private final int[] imagens;

    public ImagemPagerAdapter(Context c, int[] imagens) {
        this.ctx = c;
        this.imagens = imagens;
    }

    @Override
    public int getCount() {
        // Quantidade de views do adapter
        return imagens != null ? imagens.length : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Infla a view
        View view = LayoutInflater.from(this.ctx).inflate(R.layout.adapter_imagem, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        img.setImageResource(imagens[position]);
        // Adiciona no layout ViewGroup
        ((ViewGroup) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        // Remove do container
        ((ViewPager) container).removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // Determina se a view informada é igual ao object retornado pelo instantiateItem
        return view == object;
    }
}