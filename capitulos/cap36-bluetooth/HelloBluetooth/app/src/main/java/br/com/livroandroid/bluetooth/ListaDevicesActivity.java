package br.com.livroandroid.bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstra como buscar dispositivos bluetooth
 *
 * @author Ricardo Lecheta
 */
public class ListaDevicesActivity extends BluetoothCheckActivity implements AdapterView.OnItemClickListener {
    private ProgressDialog dialog;
    protected List<BluetoothDevice> lista;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_devices);
        listView = (ListView) findViewById(R.id.listView);
        if(btfAdapter != null) {
            // Inicia a lista com os devices pareados
            lista = new ArrayList<BluetoothDevice>(btfAdapter.getBondedDevices());
            // Registra o receiver para receber as mensagens de dispositivos pareados
            this.registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            // Register for broadcasts when discovery has finished
            this.registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();        // Garante que não existe outra busca sendo realizada
        if(btfAdapter != null) {
            if (btfAdapter.isDiscovering()) {
                btfAdapter.cancelDiscovery();
            }
            // Dispara a busca
            btfAdapter.startDiscovery();
            dialog = ProgressDialog.show(this, "Exemplo", "Buscando dispositivos bluetooth...",
                    false, true);
        }
    }

    // Receiver para receber os broadcasts do bluetooth
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        // Quantidade de dispositivos encontrados
        private int count;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("up","action: " + action);
            // Se um device foi encontrado
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Recupera o device da intent
                BluetoothDevice device =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d("up","Device: " + device);
                // Apenas insere na lista os devices que ainda não estão pareados
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    lista.add(device);
                    Toast.makeText(context, "Encontrou: " + device.getName() + ":" +
                            device.getAddress(), Toast.LENGTH_SHORT).show();
                    count++;
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                // Iniciou a busca
                count = 0;
                Toast.makeText(context, "Busca iniciada.", Toast.LENGTH_SHORT).show();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                // Terminou a busca
                Toast.makeText(context, "Busca finalizada. " + count +
                        " devices encontrados", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                // Atualiza o listview. Agora vai possuir todos os devices pareados,
                // mais os novos que foram encontrados.
                updateLista();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Garante que a busca é cancelada ao sair
        if (btfAdapter != null) {
            btfAdapter.cancelDiscovery();
            // Cancela o registro do receiver
            this.unregisterReceiver(mReceiver);
        }
    }

    private void updateLista() {
        // Cria o array com o nome de cada device
        List<String> nomes = new ArrayList<String>();
        for (BluetoothDevice device : lista) {
            // Neste exemplo, esta variável boolean sempre será true, pois esta lista é
            // somente dos pareados.
            boolean pareado = device.getBondState() == BluetoothDevice.BOND_BONDED;
            nomes.add(device.getName() + " - " + device.getAddress() +
                    (pareado ? " *pareado" : ""));
        }
        // Cria o adapter para popular o ListView
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, nomes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int idx, long id) {
        // Recupera o device selecionado
        BluetoothDevice device = lista.get(idx);
        String msg = device.getName() + " - " + device.getAddress();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        // Abre a tela do chat
        Intent intent = new Intent(this, BluetoothChatClientActivity.class);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, device);
        startActivity(intent);

    }
}
