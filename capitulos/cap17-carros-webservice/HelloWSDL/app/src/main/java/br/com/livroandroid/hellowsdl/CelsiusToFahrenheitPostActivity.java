package br.com.livroandroid.hellowsdl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Element;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import livroandroid.lib.utils.HttpHelper;
import livroandroid.lib.utils.XMLUtils;

/**
 * Demonstra como fazer uma requisição do tipo POST
 */
public class CelsiusToFahrenheitPostActivity extends AppCompatActivity {
    String URL = "http://www.w3schools.com/webservices/tempconvert.asmx/CelsiusToFahrenheit";
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
                    // Retorno: <string xmlns="http://www.w3schools.com/webservices/">33.8</string>
                    HttpHelper http = new HttpHelper();
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
                } catch (IOException e) {
                    Log.e("livroandroid", "Erro: " + e.getMessage(), e);
                }
            }
        }.start();
    }
}
