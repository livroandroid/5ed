package br.com.livroandroid.carros.domain;

import android.content.Context;
import android.util.Log;

import br.com.livroandroid.carros.R;
import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.HttpHelper;
import livroandroid.lib.utils.XMLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarroService {
    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";

    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";

    public static List<Carro> getCarros(Context context, int tipo) throws IOException {
        String tipoString = getTipo(tipo);
        String url = URL.replace("{tipo}", tipoString);
        // Faz a requisição HTTP no servidor e retorna a string com o conteúdo.
        HttpHelper http = new HttpHelper();
        String json = http.doGet(url);
        List<Carro> carros = parserJSON(context, json);
        return carros;
    }

    private static String getTipo(int tipo) {
        if (tipo == R.string.classicos) {
            return "classicos";
        } else if (tipo == R.string.esportivos) {
            return "esportivos";
        }
        return "luxo";
    }

    public static List<Carro> getCarrosFromRaw(Context context, int tipo) throws IOException {
        //String xml = readFile(context, tipo);
        //List<Carro> carros = parserXML(context, xml);
        String json = readFile(context, tipo);
        List<Carro> carros = parserJSON(context, json);

        return carros;
    }

    private static List<Carro> parserJSON(Context context, String json) throws IOException {
        List<Carro> carros = new ArrayList<Carro>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject obj = root.getJSONObject("carros");
            JSONArray jsonCarros = obj.getJSONArray("carro");
            // Insere cada carro na lista
            for (int i = 0; i < jsonCarros.length(); i++) {
                JSONObject jsonCarro = jsonCarros.getJSONObject(i);
                Carro c = new Carro();
                // Lê as informações de cada carro
                c.nome = jsonCarro.optString("nome");
                c.desc = jsonCarro.optString("desc");
                c.urlFoto = jsonCarro.optString("url_foto");
                c.urlInfo = jsonCarro.optString("url_info");
                c.urlVideo = jsonCarro.optString("url_video");
                c.latitude = jsonCarro.optString("latitude");
                c.longitude = jsonCarro.optString("longitude");
                if (LOG_ON) {
                    Log.d(TAG, "Carro " + c.nome + " > " + c.urlFoto);
                }
                carros.add(c);
            }
            if (LOG_ON) {
                Log.d(TAG, carros.size() + " encontrados.");
            }
        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
        }
        return carros;
    }


    // Faz a leitura do arquivo que está na pasta /res/raw
    private static String readFile(Context context, int tipo) throws IOException {
        if (tipo == R.string.classicos) {
            return FileUtils.readRawFileString(context, R.raw.carros_classicos, "UTF-8");
        } else if (tipo == R.string.esportivos) {
            return FileUtils.readRawFileString(context, R.raw.carros_esportivos, "UTF-8");
        }
        return FileUtils.readRawFileString(context, R.raw.carros_luxo, "UTF-8");
    }

    // Faz o parser do XML e cria a lista de carros
    private static List<Carro> parserXML(Context context, String xml) {
        List<Carro> carros = new ArrayList<Carro>();
        Element root = XMLUtils.getRoot(xml, "UTF-8");
        // Le todas as tags <carro>
        List<Node> nodeCarros = XMLUtils.getChildren(root, "carro");
        // Insere cada carro na lista
        for (Node node : nodeCarros) {
            Carro c = new Carro();
            // Lê as informações de cada carro
            c.nome = XMLUtils.getText(node, "nome");
            c.desc = XMLUtils.getText(node, "desc");
            c.urlFoto = XMLUtils.getText(node, "url_foto");
            c.urlInfo = XMLUtils.getText(node, "url_info");
            c.urlVideo = XMLUtils.getText(node, "url_video");
            c.latitude = XMLUtils.getText(node, "latitude");
            c.longitude = XMLUtils.getText(node, "longitude");
            if (LOG_ON) {
                Log.d(TAG, "Carro " + c.nome + " > " + c.urlFoto);
            }
            carros.add(c);
        }
        if (LOG_ON) {
            Log.d(TAG, carros.size() + " encontrados.");
        }
        return carros;
    }
}