package br.com.livroandroid.intents;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //Para identificar a chamada no método onActivityResult
    private static final int ACTIVITY_SIM_NAO = 1;
    private static final String TAG = "livroandroid";

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);

        String[] items = new String[]{
                "Ligar para telefone",
                "Discar para telefone",
                "Enviar E-mail",
                "Enviar SMS",
                "Abrir Browser",
                "Mapa - Lat/Lng",
                "Mapa - Endereco",
                "Mapa - Rota",

                "Compartilhar",

                "Camera Foto",
                "Camera Vídeo",

                "Visualizar Todos Contatos",
                "Visualizar Contato 1",
                "Selecionar Contato",

                "Intent customizada",
                "Intent customizada / schema",

                "Sair"

        };

        ListView listView = new ListView(this);
        setContentView(listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));


        // Solicita as permissões
        String[] permissoes = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS,
        };
        PermissionUtils.validate(this, 0, permissoes);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Alguma permissão foi negada, agora é com você :-)
                alertAndFinish();
                return;
            }
        }
        // Se chegou aqui está OK :-)
    }

    private void alertAndFinish() {
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões.");
            // Add the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            android.support.v7.app.AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    Uri uri = Uri.parse("tel:12345678");
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    startActivity(intent);
                    break;
                case 1:
                    uri = Uri.parse("tel:12345678");
                    intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                    break;
                case 2:
                    // Email
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Título do email");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Olá");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, "rlecheta@gmail.com");
                    emailIntent.setType("message/rfc822");
                    startActivity(emailIntent);
                    break;
                case 3:
                    // SMS
                    uri = Uri.parse("sms:12345678");
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    smsIntent.putExtra("sms_body", "Olá");
                    startActivity(smsIntent);
                    break;

                case 4:
                    // Browser
                    uri = Uri.parse("http://google.com");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    break;
                case 5:
                    // Mapa
                    String GEO_URI = "geo:-25.4089185,-49.3222402";
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(GEO_URI));
                    startActivity(intent);
                    break;
                case 6:
                    // Mapa
                    GEO_URI = "geo:0,0?q=Av. Manoel Ribas - Santa Felicidade, Curitiba - Paraná, Brasil";
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(GEO_URI));
                    startActivity(intent);
                    break;

                case 7:
                    // Rota
                    String rota = "http://maps.google.com/maps?saddr=-25.4089185,-49.3222402&daddr=-25.428781,-49.30925";
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(rota));
                    startActivity(intent);
                    break;

                case 8:
                    // Compartilhar
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Bla bla bla");
                    startActivity(shareIntent);
                    break;

                case 9:
                    // Tirar foto
                    // "android.media.action.IMAGE_CAPTURE
                    Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(fotoIntent, 9);
                    break;
                case 10:
                    // Gravar Vídeo
                    // android.media.action.VIDEO_CAPTURE
                    Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(videoIntent, 0);
                    break;


                case 11:
                    // Visualiza Todos os Contatos
                    uri = Uri.parse("content://com.android.contacts/contacts");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    break;
                case 12:
                    // Contato id=1
                    uri = Uri.parse("content://com.android.contacts/contacts/1");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    break;
                case 13:
                    // ACTION_PICK
                    uri = Uri.parse("content://com.android.contacts/contacts");
                    intent = new Intent(Intent.ACTION_PICK, uri);
                    startActivityForResult(intent, 13);
                    break;
                case 14:
                    // INTENT_FILTER
                    intent = new Intent("br.com.livroandroid.TESTE");
                    startActivity(intent);
                    break;
                case 15:
                    // INTENT_FILTER
                    uri = Uri.parse("livroandroid://carros/ferrari");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    break;

                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int codigo, int resultado, Intent it) {
        Log.i(TAG, "Menu.onActivityResult: " + codigo + ", resultado: " + resultado + " > " + it);

        if (codigo == 9 && resultado == Activity.RESULT_OK) {
            Bundle bundle = it.getExtras();
            if (bundle != null) {
                // Recupera o Bitmap retornado pela câmera
                Bitmap bitmap = (Bitmap) bundle.get("data");

                showToastImageView(bitmap);
            }
        } else if (codigo == 13 && resultado == Activity.RESULT_OK) {
            Uri uri = it.getData();
            Log.d(TAG, "Contato Uri: " + uri);
            Contato contato = Agenda.getContato(this, uri);

            //Toast.makeText(this,"Contato: " + contato.nome + " - " + contato.fones+ " - " + contato.emails + " - " + contato.foto, Toast.LENGTH_LONG).show();

            showToastImageView(contato.getFoto(this));
        }
    }

    private void showToastImageView(Bitmap bitmap) {
        Toast t = new Toast(this);
        ImageView imgView = new ImageView(this);
        imgView.setImageBitmap(bitmap);
        t.setView(imgView);
        t.show();
    }
}