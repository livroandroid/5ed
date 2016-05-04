package br.com.livroandroid.helloviews.ex2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

import br.com.livroandroid.helloviews.R;

public class CustomCardFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fragment);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CustomCardFragment card = new CustomCardFragment();
        Bundle args = new Bundle();
        args.putString("title","CardFragment");
        args.putString("msg","Card customizado.");
        card.setArguments(args);
        fragmentTransaction.add(R.id.cardLayout, card);
        fragmentTransaction.commit();
    }
}
