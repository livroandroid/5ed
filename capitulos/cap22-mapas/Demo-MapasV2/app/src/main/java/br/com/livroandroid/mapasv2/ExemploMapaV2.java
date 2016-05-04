package br.com.livroandroid.mapasv2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Exemplo de MapFragment por XML
 * 
 * @author Ricardo Lecheta
 *
 */
public class ExemploMapaV2 extends android.support.v4.app.FragmentActivity implements OnMapClickListener, OnCameraChangeListener {
	protected GoogleMap map;
	private SupportMapFragment mapFragment;
	protected LivroAndroidLocationSource locationSource;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_mapa_v2);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		configureMap();
	}

	protected void configureMap() {
		if(map == null) {
			mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			
			// Recupera o objeto GoogleMap
			map = mapFragment.getMap();

			if(map != null) {
				// Configura o tipo do mapa
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

				// Localização do mapa (Av. Paulista - SP)
				LatLng latLng = new LatLng(-23.564224, -46.653156);
				LatLng latLng2 = new LatLng(-23.555696, -46.662627);

				final CameraPosition position = new CameraPosition.Builder()
						.target(latLng) 	// Localização
						.bearing(130)	 	// Direção em que a cÂmera está apontando em graus
						.tilt(0) 			// Ângulo que a cÂmera está posicionada em graus (0 a 90)
						.zoom(17) 			// Zoom
						.build();

				CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);

				// Centraliza o mapa com animação de 10 segundos
				map.animateCamera(update);

				// Eventos
				map.setOnMapClickListener(this);

				// Adiciona os marcadores
				adicionarMarcador(map, latLng);
				adicionarMarcador(map, latLng2);

//				adicionaPolyline(map, latLng, latLng2);
				
//				testePolyline(map);
				testePolygon(map);

				// Localização
				locationSource = new LivroAndroidLocationSource();
				map.setMyLocationEnabled(true);
				map.setLocationSource(locationSource);
				locationSource.setLocation(latLng);
			}
		}
	}

	protected void testePolyline(GoogleMap map) {
		PolylineOptions line = new PolylineOptions();
		line.add(new LatLng(-23.564391, -46.651717));
		line.add(new LatLng(-23.565391, -46.652717));
		line.add(new LatLng(-23.564282, -46.654337));
		line.add(new LatLng(-23.563114, -46.653283));
		line.add(new LatLng(-23.564391, -46.651717));
		line.color(Color.BLUE);
		Polyline polyline = map.addPolyline(line);
	}
	
	protected void testePolygon(GoogleMap map) {
		PolygonOptions p = new PolygonOptions();
		p.add(new LatLng(-23.564391, -46.651717));
		p.add(new LatLng(-23.565391, -46.652717));
		p.add(new LatLng(-23.564282, -46.654337));
		p.add(new LatLng(-23.563114, -46.653283));
//		p.add(new LatLng(-23.564391, -46.651717));
		p.strokeColor(Color.GREEN);
		p.fillColor(Color.RED);
		Polygon polygon = map.addPolygon(p);
		polygon.setFillColor(Color.YELLOW);
	}

	protected void adicionaPolyline(GoogleMap map2,LatLng latLng, LatLng latLng2) {
		// Desenha uma linha entre dois pontos
		PolylineOptions line = new PolylineOptions();
		line.add(new LatLng(latLng.latitude, latLng.longitude));
		line.add(new LatLng(latLng2.latitude, latLng2.longitude));
		line.color(Color.BLUE);
		Polyline polyline = map.addPolyline(line);
		polyline.setGeodesic(true);
	}

	// Adiciona um marcador
	private void adicionarMarcador(GoogleMap map, LatLng latLng) {
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(latLng).title("Meu Marcador").snippet("Livro Android");
		markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));

		Marker marker = map.addMarker(markerOptions);

//		map.setOnMarkerClickListener(new OnMarkerClickListener() {
//			@Override
//			public boolean onMarkerClick(Marker marker) {
//				LatLng lartLng = marker.getPosition();
//				Toast.makeText(getBaseContext(), "Marcador: " + marker.getTitle() + " > " + lartLng, Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		});

		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker marker) {
				LatLng lartLng = marker.getPosition();
				Toast.makeText(getBaseContext(), "Clicou no: " + marker.getTitle() + " > " + lartLng, Toast.LENGTH_SHORT).show();
			}
		});

		// Customiza a janela ao clicar em um marcador
		map.setInfoWindowAdapter(new InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {
				LinearLayout linear = (LinearLayout) this.getInfoContents(marker);
				// Borda imagem 9-patch
				linear.setBackgroundResource(R.drawable.janela_marker);
				return linear;
			}
			@Override
			public View getInfoContents(Marker marker) {
				// View com o conteúdo
				LinearLayout linear = new LinearLayout(getBaseContext());
				linear.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				linear.setOrientation(LinearLayout.VERTICAL);

				TextView t = new TextView(getBaseContext());
				t.setText("*** View customizada *** ");
				t.setTextColor(Color.BLACK);
				t.setGravity(Gravity.CENTER);
				linear.addView(t);
				
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

	@Override
	public void onCameraChange(CameraPosition position) {
		TextView tDebug = (TextView) findViewById(R.id.tDebug);
		tDebug.setText("Posição: " + position);
	}

	@Override
	public void onMapClick(LatLng latLng) {
		TextView tDebug = (TextView) findViewById(R.id.tDebug);
		CameraUpdate update = CameraUpdateFactory.newLatLng(latLng);
		tDebug.setText("Posição: " + latLng);
//		Toast.makeText(getBaseContext(), "Click: " + latLng, Toast.LENGTH_SHORT).show();
//		map.animateCamera(update);
	}
}