package br.com.livroandroid.hellowsdl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

import br.com.livroandroid.hellowsdl.utils.HttpHelper;
import br.com.livroandroid.hellowsdl.utils.XMLUtils;

/**
 * Demonstra como fazer uma requisição do tipo POST
 */
public class CelsiusToFahrenheitPostActivity extends AppCompatActivity {
    String URL = "http://www.w3schools.com/xml/tempconvert.asmx/CelsiusToFahrenheit";
    private EditText tCelcius;
    private EditText tFahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_celsius_to_fahrenheit);
        tCelcius = (EditText) findViewById(R.id.tCelcius);
        tFahrenheit = (EditText) findViewById(R.id.tFahrenheit);
    }

    public void onClickConverter(View view) {

        new Thread() {
            @Override
            public void run() {
                String celcius = tCelcius.getText().toString();

                // Parâmetros para enviar por post
                Map<String, String> params = new HashMap<String, String>();
                params.put("Celsius", celcius);

                try {
                    /**
                     * No ultimo teste que fiz este WS estava fora do ar :-(
                     */

                    // Retorno: <string xmlns="http://www.w3schools.com/webservices/">33.8</string>
                    HttpHelper http = new HttpHelper();
                    http.LOG_ON = true;
                    String s = http.doPost(URL, params, "UTF-8");

                    Element root = XMLUtils.getRoot(s, "UTF-8");

                    // Lê o texto do XML
                    final String fahrenheit = XMLUtils.getText(root);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tFahrenheit.setText(fahrenheit);
                        }
                    });
                } catch (final Exception e) {
                    Log.e("livroandroid", "Erro: " + e.getMessage(), e);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }.start();
    }
}
