package br.com.livroandroid.actionbar;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

/**
 * Created by Ricardo Lecheta on 24/12/2014.
 */
public class MyTabListener implements ActionBar.TabListener {
    private Context context;
    private int tabIdx;

    public MyTabListener(Context context, int tabIdx) {
        this.context = context;
        this.tabIdx = tabIdx;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Chamado ao selecionar uma tab
        Toast.makeText(context, "Selecionou a tab: " + tabIdx, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Chamado quando a tab perde o foco (se outra tab é selecionada)
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Chamado quando uma tab é selecionada novamente.
    }
}
