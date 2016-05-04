package br.com.livroandroid.hellomaterial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;


/*
* https://developer.android.com/reference/android/support/v7/widget/CardView.html
*
* http://www.google.com/design/spec/components/cards.html#cards-usage
*
* */
public class ExemploCardViewActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private CardView cardView;
    private SeekBar seekBar1;
    private SeekBar seekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exemplo_card_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardView = (CardView) findViewById(R.id.cardView);

        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar1.setOnSeekBarChangeListener(this);

        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar2.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == this.seekBar1) {
            cardView.setCardElevation(progress);
        } else if (seekBar == this.seekBar2) {
            cardView.setRadius(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
