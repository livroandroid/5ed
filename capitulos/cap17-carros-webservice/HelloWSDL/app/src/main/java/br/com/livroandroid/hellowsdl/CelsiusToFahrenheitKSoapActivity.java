package br.com.livroandroid.hellowsdl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.URL;

/**
 * Demonstra como utilizar a lib KSOAP
 */
public class CelsiusToFahrenheitKSoapActivity extends AppCompatActivity {
    private static final String URL = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
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
                try {
                    String celcius = tCelcius.getText().toString();
                    final String fahrenheit = CelsiusToFahrenheit("http://www.w3schools.com/webservices/tempconvert.asmx", celcius);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           tFahrenheit.setText(fahrenheit);
                        }
                    });
                } catch (Exception e) {
                    Log.e("livroandroid", "Erro: " + e.getMessage(), e);
                }
            }
        }.start();
    }

    public String CelsiusToFahrenheit(String url, String celsius) throws Exception {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://www.w3schools.com/webservices/", "CelsiusToFahrenheit");
        soapReq.addProperty("Celsius", celsius);
        soapEnvelope.setOutputSoapObject(soapReq);
        int timeOut = 60000;
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            // Faz a chamada
            httpTransport.call(URL, soapEnvelope);
            // Lê o retorno
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault) {
                SoapFault fault = (SoapFault) retObj;
                Exception ex = new Exception(fault.faultstring);
                throw ex;
            } else {
                // Retorno OK
                SoapObject result = (SoapObject) retObj;
                if (result.getPropertyCount() > 0) {
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                        SoapPrimitive j = (SoapPrimitive) obj;
                        String resultVariable = j.toString();
                        return resultVariable;
                    } else if (obj != null && obj instanceof String) {
                        String resultVariable = (String) obj;
                        return resultVariable;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return "";
    }
}
