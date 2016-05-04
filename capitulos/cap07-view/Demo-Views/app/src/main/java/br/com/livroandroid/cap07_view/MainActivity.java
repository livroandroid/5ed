package br.com.livroandroid.cap07_view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.cap07_view.canvas.ExemploMinhaViewActivity;
import br.com.livroandroid.cap07_view.canvas.TouchScreenViewActivity;

/**
 * Exemplos de Layouts
 *
 * @author rlecheta
 */
public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String[] items = new String[]{
                "Texto e Cores",
                "AutoCompleteTextView",
                "ImageButton",
                "ToggleButton",
                "Check e Radio",
                "Spinner",
                "ProgressDialog",
                "ProgressBar",
                "AlertDialog",
                "ListView - exemplo simples",
                "ListView - planetas",
                "GridView",
                "Gallery",
                "ViewPager",
                "ViewPager + TitleStrip",
                "ViewPager + TabStrip",
                "ImageSwitcher",
                "WebView",
                "MinhaView",
                "Touch"

        };

        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        try {
            switch (position) {
                case 0:
                    startActivity(new Intent(this, ExemploTextoCoresActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(this, ExemploAutoCompleteTextViewActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(this, ExemploImageButtonActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(this, ExemploToggleButtonActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(this, ExemploCheckRadioFormActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(this, ExemploSpinnerActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(this, ExemploProgressDialogActivity.class));
                    break;
                case 7:
                    startActivity(new Intent(this, ExemploProgressBarActivity.class));
                    break;
                case 8:
                    Builder builder = new Builder(this);
                    builder.setIcon(R.drawable.ic_launcher);
                    builder.setTitle("Título");
                    builder.setMessage("Mensagem");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getBaseContext(), "Clicou em Sim!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getBaseContext(), "Clicou em Não!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;
                case 9:
                    startActivity(new Intent(this, ExemploListViewActivity.class));
                    break;
                case 10:
                    startActivity(new Intent(this, ExemploListViewPlanetasActivity.class));
                    break;
                case 11:
                    startActivity(new Intent(this, ExemploGridViewActivity.class));
                    break;
                case 12:
                    startActivity(new Intent(this, ExemploGalleryActivity.class));
                    break;
                case 13:
                    startActivity(new Intent(this, ExemploViewPagerActivity.class));
                    break;
                case 14:
                    startActivity(new Intent(this, ExemploViewPagerTitleStripActivity.class));
                    break;
                case 15:
                    startActivity(new Intent(this, ExemploViewPagerTabStripActivity.class));
                    break;
                case 16:
                    startActivity(new Intent(this, ExemploImageSwitcherActivity.class));
                    break;
                case 17:
                    startActivity(new Intent(this, ExemploWebViewActivity.class));
                    break;
                case 18:
                    startActivity(new Intent(this, ExemploMinhaViewActivity.class));
                    break;
                case 19:
                    startActivity(new Intent(this, TouchScreenViewActivity.class));
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