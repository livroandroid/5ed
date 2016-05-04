package br.com.livroandroid.helloviews.ex4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.widget.Toast;

import java.util.List;

import br.com.livroandroid.helloviews.R;
import br.com.livroandroid.shared.Carro;

/**
 * Created by ricardo on 19/04/15.
 */
public class HelloListViewActivity extends Activity implements WearableListView.ClickListener {
    private List<Carro> carros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_listview);
        WearableListView listView = (WearableListView) findViewById(R.id.listView);
        carros = Carro.getListEsportivos();
        listView.setAdapter(new CarroAdapter(this, carros));
        listView.setClickListener(this);
    }
    @Override
    public void onClick(WearableListView.ViewHolder v) {
        Integer position = (Integer) v.itemView.getTag();
        Carro c = carros.get(position);
        Toast.makeText(this,"Carro: " + c.nome,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,CarroActivity.class);
        intent.putExtra("carro",c);
        startActivity(intent);
    }
    @Override
    public void onTopEmptyRegionClick() {}
}