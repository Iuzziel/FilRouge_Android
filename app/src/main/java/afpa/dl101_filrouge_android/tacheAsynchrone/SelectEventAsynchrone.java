package afpa.dl101_filrouge_android.tacheAsynchrone;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Vector;

import afpa.dl101_filrouge_android.MainActivity;
import afpa.dl101_filrouge_android.database.EvenementManager;
import afpa.dl101_filrouge_android.objet.Evenement;

//public class SelectEventAsynchrone extends AsyncTask<Integer, Integer, Vector<Vector<Evenement>>> {
public class SelectEventAsynchrone extends AsyncTask<Integer, Integer, Vector<Evenement>> {

    protected void onPreExecute() {
        super.onPreExecute();
    }

    //protected Vector<Vector<Evenement>> doInBackground(Integer... tDate) {
    protected Vector<Evenement> doInBackground(Integer... tDate) {
        EvenementManager em = new EvenementManager(MainActivity.mainContext);
        /*Les managers ne sont pas static a cause du context,
        c'est pour cela que le MainActivity a un context public static*/
        //Vector<Vector<Evenement>> vvEvent = new Vector<>();
        Vector<Evenement> vEvent = new Vector<>();
        if (tDate.length > 0) {
            for (Integer element : tDate) {
                /*
                try {
                    vvEvent.add(em.getEvenementJour(element));
                } catch (Exception e) {
                    Log.e("AsyncTask", "Echec select dans la base");
                    e.printStackTrace();
                }
                */
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

    /*
    protected void onPostExecute(Vector<Vector<Evenement>> result) {
        super.onPostExecute(result);
    }
    */
    protected void onPostExecute(Vector<Evenement> result) {
        super.onPostExecute(result);
    }

}
