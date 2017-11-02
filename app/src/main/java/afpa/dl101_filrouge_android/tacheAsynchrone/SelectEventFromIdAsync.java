package afpa.dl101_filrouge_android.tacheAsynchrone;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Vector;

import afpa.dl101_filrouge_android.database.EvenementManager;
import afpa.dl101_filrouge_android.objet.Evenement;

public class SelectEventFromIdAsync extends AsyncTask<Integer, Integer, Evenement> {
    private Context mContext;

    public SelectEventFromIdAsync(Context context) {
        mContext = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Evenement doInBackground(Integer... tID) {
        EvenementManager em = new EvenementManager(mContext);
        /* Les managers ne sont pas static a cause du context,
        c'est pour cela que le MainActivity a un context public static */
        Evenement nEvent = null;
        if (tID.length > 0) {
            for (Integer id : tID) {
                try {
                    nEvent = em.getEvenementFromId(id);
                } catch (Exception e) {
                    Log.e("AsyncTask", "Echec select dans la base");
                    e.printStackTrace();
                }
            }
        }
        return nEvent;
    }

    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(Evenement result) {
        super.onPostExecute(result);
    }
}
