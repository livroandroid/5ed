package br.com.livroandroid.cap07_view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.List;

public class ExemploViewPagerTabStripActivity extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_exemplo_view_pager_tab_strip);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Planetas
        List<Planeta> planetas = Planeta.getPlanetas();

        // ViewPager
        ViewPager g = (ViewPager) findViewById(R.id.viewPager);
        g.setAdapter(new PlanetasPagerAdapter(this, planetas));
    }
}
