package afpa.dl101_filrouge_android.vues;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.objet.Evenement;
import afpa.dl101_filrouge_android.objet.EvenementAdapter;
import afpa.dl101_filrouge_android.tacheAsynchrone.SelectEventAsynchrone;

public class ListEvenement extends AppCompatActivity {
    private Vector<Evenement> vListEvenement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_evenement);

        initControl();
    }

    private void initControl() {
        SelectEventAsynchrone selectEventAsynchrone = new SelectEventAsynchrone();
        try {
            Log.d("ListEvent", "Entre dans le try/catch du onCreate");
            Vector<Evenement> vSelectEvent = selectEventAsynchrone.execute(20171026).get();
            Log.d("ListEvent", "Entre dans le if. size : " + vSelectEvent.size());
            Log.d("ListEvent", "Entre dans le if. Class Event 1: " + vSelectEvent.elementAt(0).getClass());
            if (vSelectEvent.size() > 0) {
                for (Evenement vTemp : vSelectEvent) {
                    Log.d("ListEvent", "Boucle" + vTemp.getTitre());
                }
                ArrayList<Evenement> listEvent = getAListOfEvent(vSelectEvent);
                EvenementAdapter adapter = new EvenementAdapter(this, listEvent);
                ListView list = (ListView) findViewById(R.id.listEvent);
                list.setAdapter(adapter);
            }
            /*
            Vector<Vector<Evenement>> vvSelectEvent = selectEventAsynchrone.execute(20171026).get();
            Vector<Evenement> vvSelectEvent = selectEventAsynchrone.execute(20171026).get();
            Log.d("ListEvent", "Entre dans le if. Titre Event 1: " + vvSelectEvent.elementAt(0).elementAt(0).getTitre());
            if (vvSelectEvent.size() > 0) {
                for (Vector<Evenement> vTemp:vvSelectEvent) {
                    Log.d("ListEvent", "Boucle" + vTemp.elementAt(0).getTitre());
                    vTemp.size();
                    vTemp.elementAt(0);
                }
                ArrayList<Evenement> listEvent = getAListOfEvent(vvSelectEvent.firstElement());
                EvenementAdapter adapter = new EvenementAdapter(this, listEvent);
                ListView list = (ListView) findViewById(R.id.listEvent);
                list.setAdapter(adapter);
            }
            */
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Evenement> getAListOfEvent(Vector<Evenement> vEvenement) {
        ArrayList<Evenement> listEvent = new ArrayList<>();
        for (Evenement e : vEvenement) {
            listEvent.add(e);
        }
        return listEvent;
    }
}
