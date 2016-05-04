package br.com.livroandroid.handler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Exemplos de Layouts
 *
 * @author rlecheta
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        String[] items = new String[] {
                "Handler - message",
                "Handler - runnable",
                "Download imagem - Thread",
                "Re-Download imagem - Handler",
                "Contador",
        };

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(onListItemClick());
    }

    private AdapterView.OnItemClickListener onListItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(getBaseContext(),DemoHandlerMessageActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(getBaseContext(),DemoHandlerRunnableActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(getBaseContext(),DownloadImagemActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(getBaseContext(),ReDownloadImagemActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(getBaseContext(),ContadorActivity.class));
                            break;

                        default:
                            finish();
                            break;
                    }
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}