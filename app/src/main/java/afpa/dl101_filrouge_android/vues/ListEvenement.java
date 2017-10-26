package afpa.dl101_filrouge_android.vues;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.objet.Evenement;
import afpa.dl101_filrouge_android.objet.EvenementAdapter;
import afpa.dl101_filrouge_android.tacheAsynchrone.SelectEventAsynchrone;

public class ListEvenement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_evenement);

        initControl();
    }

    private void initControl() {
        SelectEventAsynchrone selectEventAsynchrone = new SelectEventAsynchrone(this);
        try {
            Vector<Evenement> vSelectEvent = selectEventAsynchrone.execute(20171026).get();
            if (!vSelectEvent.isEmpty()) {
                ArrayList<Evenement> listEvent = getAListOfEvent(vSelectEvent);
                EvenementAdapter adapter = new EvenementAdapter(this, listEvent);
                ListView list = (ListView) findViewById(R.id.listEvent);
                list.setAdapter(adapter);
            } else {
                System.exit(0);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Evenement> getAListOfEvent(Vector<Evenement> vEvenement) {
        ArrayList<Evenement> listEvent = new ArrayList<>();
        for (Evenement e : vEvenement)
            listEvent.add(e);
        return listEvent;
    }
}
