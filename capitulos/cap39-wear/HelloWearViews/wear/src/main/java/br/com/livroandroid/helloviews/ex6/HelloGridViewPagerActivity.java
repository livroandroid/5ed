package br.com.livroandroid.helloviews.ex6;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;

import java.util.List;

import br.com.livroandroid.helloviews.R;
import br.com.livroandroid.shared.Carro;
import livroandroid.lib.wear.WearUtil;

/**
 * Created by ricardo on 19/04/15.
 */
public class HelloGridViewPagerActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_gridviewpager);
        List<Carro> classicos = Carro.getListClassicos();
        List<Carro> esportivos = Carro.getListEsportivos();
        List<Carro> luxo = Carro.getListLuxo();
        GridViewPager viewPager = (GridViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CarrosGridPagerAdapter(this, getFragmentManager(), classicos, esportivos, luxo));
    }
}