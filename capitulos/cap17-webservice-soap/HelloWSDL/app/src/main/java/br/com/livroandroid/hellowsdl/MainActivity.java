package br.com.livroandroid.hellowsdl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Exemplos de como chamar um web service WSDL: http://www.w3schools.com/webservices/tempconvert.asmx
 *
 * @author rlecheta
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String[] items = new String[]{
                "POST",
                "KSoap manual",
                "KSoap com código gerado"
        };

        ListView listView = new ListView(this);
        setContentView(listView);
        listView.setOnItemClickListener(OnItemClick());
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    private AdapterView.OnItemClickListener OnItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(getBaseContext(), CelsiusToFahrenheitPostActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(getBaseContext(), CelsiusToFahrenheitKSoapActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(getBaseContext(), CelsiusToFahrenheitActivity.class));
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