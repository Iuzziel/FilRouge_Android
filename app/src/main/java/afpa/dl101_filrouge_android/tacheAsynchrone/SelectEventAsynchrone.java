package afpa.dl101_filrouge_android.tacheAsynchrone;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Vector;

import afpa.dl101_filrouge_android.database.EvenementManager;
import afpa.dl101_filrouge_android.objet.Evenement;

public class SelectEventAsynchrone extends AsyncTask<Integer, Integer, Vector<Evenement>> {
    private Context mContext;

    public SelectEventAsynchrone(Context context) {
        mContext = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Vector<Evenement> doInBackground(Integer... tDate) {
        EvenementManager em = new EvenementManager(mContext);
        /* Les managers ne sont pas static a cause du context,
        c'est pour cela que le MainActivity a un context public static */
        Vector<Evenement> vEvent = new Vector<>();
        if (tDate.length > 0) {
            for (Integer element : tDate) {
                try {
                    vEvent = em.getEvenementJour(element);
                } catch (Exception e) {
                    Log.e("AsyncTask", "Echec select dans la base");
                    e.printStackTrace();
                }
            }
        }
        return vEvent;
    }

    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(Vector<Evenement> result) {
        super.onPostExecute(result);
    }
}
