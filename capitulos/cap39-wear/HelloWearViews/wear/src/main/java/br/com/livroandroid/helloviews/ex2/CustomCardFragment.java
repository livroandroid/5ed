package br.com.livroandroid.helloviews.ex2;

import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.livroandroid.helloviews.R;

/**
 * Created by ricardo on 19/04/15.
 */
public class CustomCardFragment extends CardFragment {
    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_card, container, false);
        // title
        TextView tTitle = (TextView) view.findViewById(R.id.tTitle);
        tTitle.setText(getArguments().getString("title"));
        // content
        TextView tMsg = (TextView) view.findViewById(R.id.tMsg);
        tMsg.setText(getArguments().getString("msg"));
        return view;
    }
}