package br.com.livroandroid.helloviews.ex1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;

import br.com.livroandroid.helloviews.R;

public class CardFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fragment);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CardFragment cardFragment = CardFragment.create("Hello",
                "Meu Primeiro CardView",
                R.mipmap.ic_launcher);
        fragmentTransaction.add(R.id.cardLayout, cardFragment);
        fragmentTransaction.commit();
    }
}
