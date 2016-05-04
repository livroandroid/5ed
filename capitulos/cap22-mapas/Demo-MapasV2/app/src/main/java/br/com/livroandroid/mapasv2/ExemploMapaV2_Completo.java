package br.com.livroandroid.mapasv2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Exemplo de MapFragment por XML
 * 
 * @author ricardo
 * 
 */
public class ExemploMapaV2_Completo extends Activity {

	protected GoogleMap map;
	protected MarkerOptions marker;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_mapa_v2);

		MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		map = mapFragment.getMap();

		// Configura o modo do mapa
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

		// Coordenada da rua sete de setembro - Curitiba
		LatLng latLng = new LatLng(-25.442580, -49.279840);
		CameraPosition.Builder builder = new CameraPosition.Builder().target(latLng).zoom(15.5f);
		CameraPosition location = builder.build();

		// Anima o mapa até esta coordenada
		CameraUpdate position = CameraUpdateFactory.newCameraPosition(location);
		map.animateCamera(position);

		// My Location
		map.setMyLocationEnabled(true);

		// Listener ao clicar no mapa
		map.setOnMapClickListener(new OnMapClickListener() {
			@Override
			public void onMapClick(LatLng latLng) {
				Toast.makeText(getBaseContext(),"LatLng: " + latLng, Toast.LENGTH_SHORT).show();
				Log.i("livro",latLng.toString());
			}
		});

		//instancia um novo Polyline e adiciona pontos para definir um retângulo
		PolylineOptions rectOptions = new PolylineOptions()
		    .add(new LatLng(-25.440824,-49.280769))
		    .add(new LatLng(-25.440867,-49.277876))
		    .add(new LatLng(-25.443393,-49.278331))
			.add(new LatLng(-25.443436,-49.281546))
			.add(new LatLng(-25.440824,-49.280769));

		// configura a cor do retÂngulo para azul
		rectOptions.color(Color.BLUE);

		Polyline polyline = map.addPolyline(rectOptions);
		polyline.setGeodesic(true);

		marcadores(map, latLng);
	}

	// Demonstra como adicionar um marcador
	protected void marcadores(GoogleMap map, LatLng latLng) {
		// Adiciona um marcador
		marker = new MarkerOptions().position(latLng).title("Meu Marcador")
				.snippet("Exemplo de mensagem");
		marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
		map.addMarker(marker);

		// Customiza a janela ao clicar em um marcador
		map.setInfoWindowAdapter(new InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {
				FrameLayout frame = new FrameLayout(getBaseContext());
				frame.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				frame.setBackgroundResource(R.drawable.janela_marker);
				// Janela da borda
				return frame;
			}
			@Override
			public View getInfoContents(Marker marker) {
				// View com o conteúdo
				LinearLayout linear = new LinearLayout(getBaseContext());
				linear.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				linear.setOrientation(LinearLayout.VERTICAL);
				
				TextView tTitle = new TextView(getBaseContext());
				tTitle.setText(marker.getTitle());
				tTitle.setTextColor(Color.RED);
				linear.addView(tTitle);
				
				TextView tSnippet = new TextView(getBaseContext());
				tSnippet.setText(marker.getSnippet());
				tSnippet.setTextColor(Color.BLUE);
				linear.addView(tSnippet);

				return linear;
			}
		});
	}
}