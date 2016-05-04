package br.com.livroandroid.hellowsdl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.Wsdl2Code.WebServices.TempConvert.TempConvert;

/**
 * Utiliza a classe TempConvert que foi gerada por: http://www.wsdl2code.com
 */
public class CelsiusToFahrenheitActivity extends AppCompatActivity {
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
                TempConvert t = new TempConvert();
                String celcius = tCelcius.getText().toString();
                final String fahrenheit = t.CelsiusToFahrenheit(celcius);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tFahrenheit.setText(fahrenheit);
                    }
                });
            }
        }.start();
    }
}
