package br.com.livroandroid.bluetooth;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Splash para listar as permissões.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Valida lista de permissões.
        String permissions[] = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
        };
        boolean ok = PermissionUtils.validate(this,0, permissions);

        if(ok) {
            // Tudo OK, pode entrar.
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        for (int result: grantResults) {
            if(result == PackageManager.PERMISSION_DENIED) {
                // Negou a permissão. Mostra alerta e fecha.
                Toast.makeText(SplashActivity.this, "Aceite as permissions", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }
        
        // ~Permissões concedidas, pode entrar.
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
