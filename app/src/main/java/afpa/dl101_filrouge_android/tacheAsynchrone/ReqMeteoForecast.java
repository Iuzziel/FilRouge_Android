package afpa.dl101_filrouge_android.tacheAsynchrone;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import afpa.dl101_filrouge_android.objet.Meteo;

public class ReqMeteoForecast extends AsyncTask<Meteo, Void, Map<String, String>> {
    private Meteo meteo;
    private final String apikey = "691333b93c35e1c6174fac1de1841c58";
    private final String http = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private final String httpFin = ",fr&type=like&APPID=" + apikey;
    private final int READ_TIMEOUT = 10000;// Miliseconde
    private final int CONNECT_TIMEOUT = 10000;// Miliseconde

    // Tests qui fonctionnent :
    // http://api.openweathermap.org/data/2.5/forecast?q=Caen,fr&type=like&APPID=691333b93c35e1c6174fac1de1841c58
    // http://api.openweathermap.org/data/2.5/forecast?q=Caen&type=like&APPID=691333b93c35e1c6174fac1de1841c58
    public ReqMeteoForecast(Meteo meteo) {
        this.meteo = meteo;
    }

    @Override
    protected Map<String, String> doInBackground(Meteo... meteo) {
        String reponse = "";
        // Partie requete sur le serveur de l'api
        for (Meteo jMeteo : meteo) {
            try {
                String requete = http + jMeteo.getLocation() + httpFin;
                URL url = new URL(requete);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setReadTimeout(READ_TIMEOUT);
                urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                try {
                    urlConnection.connect();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();
                    while (data != -1) {
                        char current = (char) data;
                        data = isw.read();
                        reponse += current;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Partie recuperation et parsing de la reponse
        Map<String, String> mapMeteo = new HashMap<String, String>();
        try {
            JSONObject jsonResult = new JSONObject(reponse);

            Log.e("reqmetfor", jsonResult.toString());
            for (int i = 0; i < jsonResult.getInt("cnt"); i++) {
                mapMeteo.put(jsonResult.getJSONArray("list").getJSONObject(i).getString("dt_txt"),
                        jsonResult.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mapMeteo;
    }

    @Override
    protected void onPostExecute(Map<String, String> result) {
        super.onPostExecute(result);
    }

}