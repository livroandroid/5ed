package br.com.livroandroid.mapasv2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.gms.maps.MapFragment;

/**
 * Exemplo de MapFragment por API
 * 
 * @author ricardo
 *
 */
public class ExemploMapaV2_API extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_mapa_v2_api);

        /**
         * Se você quiser adicionar o mapa pela API
         */
		MapFragment map = new MapFragment();
		FragmentTransaction t = getFragmentManager().beginTransaction();
		t.add(R.id.layoutMap, map, null);
		t.commit();
	}
}