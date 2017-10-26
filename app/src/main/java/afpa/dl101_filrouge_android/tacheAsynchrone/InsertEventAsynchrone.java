package afpa.dl101_filrouge_android.tacheAsynchrone;

import android.os.AsyncTask;
import android.util.Log;

import afpa.dl101_filrouge_android.MainActivity;
import afpa.dl101_filrouge_android.database.EvenementManager;
import afpa.dl101_filrouge_android.objet.Evenement;

public class InsertEventAsynchrone extends AsyncTask<Evenement, Integer, Long> {

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Long doInBackground(Evenement... evenement) {
        EvenementManager em = new EvenementManager(MainActivity.mainContext);
        /*Les managers ne sont pas static a cause du context,
        c'est pour cela que le MainActivity a un context public static*/
        if (evenement.length > 0) {
            Long tmp = null;
            for (Evenement element : evenement) {
                tmp = em.insertEvenement(element);
                Log.d("AsyncTask", "Insert dans la base");
            }
            return tmp;
        }
        return 0l;
    }

    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
    }

}
