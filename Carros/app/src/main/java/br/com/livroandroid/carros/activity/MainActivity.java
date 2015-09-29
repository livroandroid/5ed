package br.com.livroandroid.carros.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.TabsAdapter;
import br.com.livroandroid.carros.fragments.AboutDialog;
import br.com.livroandroid.carros.fragments.CarrosFragment;
import br.com.livroandroid.carros.fragments.CarrosTabFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setupNavDrawer();

        setupViewPagerTabs();

        // FAB
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snack(v, "Exemplo de FAB Button.");
            }
        });
    }
    // Configura o ViewPager + Tabs
    private void setupViewPagerTabs() {
        // ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getContext(), getSupportFragmentManager()));
        // Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // Cria as tabs com o mesmo adapter utilizado pelo ViewPager
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(), R.color.white);
        // Cor branca no texto (o fundo azul foi definido no layout)
        tabLayout.setTabTextColors(cor, cor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            AboutDialog.showAbout(getSupportFragmentManager());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
