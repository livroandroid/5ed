package br.com.livroandroid.cap06_layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExemploScrollViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_exemplo_scrollview);
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);
		for (int i = 0; i < 100; i++) {

			// Instancia o TextView inflando o arquivo de layout
			LayoutInflater inflater = LayoutInflater.from(this);
			TextView text = (TextView) inflater.inflate(R.layout.inflate_textview, layout, false);

			// Instancia o TextView pela API
//			TextView text = new TextView(this);
//			text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

			text.setText("Texto: " + i);
			layout.addView(text);
		}
	}
}
