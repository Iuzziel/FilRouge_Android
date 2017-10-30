package afpa.dl101_filrouge_android.tacheAsynchrone;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import afpa.dl101_filrouge_android.metier.ToolBox;
import afpa.dl101_filrouge_android.objet.Meteo;

public class RequeteMeteoAsync extends AsyncTask<String, Void, Meteo> {
    private Meteo meteo;
    private final String apikey = "691333b93c35e1c6174fac1de1841c58";
    private final String http = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final String httpFin = ",fr&type=like&APPID=" + apikey;
    private final int READ_TIMEOUT = 10000;// Miliseconde
    private final int CONNECT_TIMEOUT = 10000;// Miliseconde

    // Tests qui fonctionnent :
    // http://api.openweathermap.org/data/2.5/weather?q=Caen,fr&type=like&APPID=691333b93c35e1c6174fac1de1841c58
    // http://api.openweathermap.org/data/2.5/weather?q=Caen&APPID=691333b93c35e1c6174fac1de1841c58
    public RequeteMeteoAsync(Meteo meteo) {
        this.meteo = meteo;
    }

    @Override
    protected Meteo doInBackground(String... emplacements) {
        String reponse = "";
        // Partie requete sur le serveur de l'api
        for (String emplacement : emplacements) {
            try {
                String requete = http + emplacement + httpFin;
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
        try {
            JSONObject jsonResult = new JSONObject(reponse);

            String description = jsonResult.getJSONArray("weather").getJSONObject(0).getString("description");

            double temperature = jsonResult.getJSONObject("main").getDouble("temp");
            temperature = ToolBox.ConvTempToFarenheit(temperature);

            double pressure = jsonResult.getJSONObject("main").getDouble("pressure");

            double humidity = jsonResult.getJSONObject("main").getDouble("humidity");

            double windSpeed = jsonResult.getJSONObject("wind").getDouble("speed");

            double cloudPerc = jsonResult.getJSONObject("cloud").getDouble("all");

            this.meteo.setDescription(description);
            this.meteo.setTemperature(temperature);
            this.meteo.setPressure(pressure);
            this.meteo.setHumidity(humidity);
            this.meteo.setWindSpeed(windSpeed);
            this.meteo.setCloudPerc(cloudPerc);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.meteo;
    }

    @Override
    protected void onPostExecute(Meteo result) {
        super.onPostExecute(result);
    }

}