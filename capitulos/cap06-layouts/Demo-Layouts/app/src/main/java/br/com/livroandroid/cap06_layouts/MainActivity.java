package br.com.livroandroid.cap06_layouts;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Exemplos de Layouts
 * 
 * @author rlecheta
 * '
 */
public class MainActivity extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { 
				"Exemplos de layout",
				"LinearLayout pela API",
				"TableLayout pela API",
				"ScrollView"

		};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		try {
			switch (position) {
			case 0:
				Toast.makeText(this, "Abra os arquivos de layout XML e veja a pré-visualização no editor.", Toast.LENGTH_LONG).show();
				break;
			case 1:
				startActivity(new Intent(this,ExemploLinearLayoutAPIActivity.class));
				break;
			case 2:
				startActivity(new Intent(this,ExemploTableLayoutAPIActivity.class));
				break;
			case 3:
				startActivity(new Intent(this,ExemploScrollViewActivity.class));
				break;

			default:
				finish();
				break;
		}
		} catch (Exception e) {
			Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}