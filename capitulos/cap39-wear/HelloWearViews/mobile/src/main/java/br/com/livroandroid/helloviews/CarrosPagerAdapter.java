package br.com.livroandroid.helloviews;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.livroandroid.shared.Carro;

/**
 * Created by ricardo on 19/04/15.
 */
public class CarrosPagerAdapter extends PagerAdapter {
    private final LayoutInflater mInflater;
    private final List<Carro> carros;

    public CarrosPagerAdapter(Context context, List<Carro> carros) {
        mInflater = LayoutInflater.from(context);
        this.carros = carros;
    }

    @Override
    public int getCount() {
        return carros != null ? carros.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Infla a view
        View view = mInflater.inflate(R.layout.adapter_carro_pager, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        Carro c = carros.get(position);
        img.setImageResource(c.img);
        ((ViewGroup) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
