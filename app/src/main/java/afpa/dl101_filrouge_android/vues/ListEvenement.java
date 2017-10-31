package afpa.dl101_filrouge_android.vues;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.objet.Evenement;
import afpa.dl101_filrouge_android.objet.EvenementAdapter;
import afpa.dl101_filrouge_android.tacheAsynchrone.SelectEventAsynchrone;

public class ListEvenement extends AppCompatActivity {
    private int rDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_evenement);
        Intent intent = getIntent();
        rDate = intent.getIntExtra("rDate", 0);
        Log.d("rDate", String.valueOf(rDate));
        try {
            initControl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initControl() {
        SelectEventAsynchrone selectEventAsynchrone = new SelectEventAsynchrone(this);
        try {
            Vector<Evenement> vSelectEvent = selectEventAsynchrone.execute(rDate).get();

            if (!(vSelectEvent.isEmpty())) {
                ArrayList<Evenement> listEvent = getAListOfEvent(vSelectEvent);
                final EvenementAdapter adapter = new EvenementAdapter(this, listEvent);
                ListView list = (ListView) findViewById(R.id.listEvent);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object eventSelect = adapter.getItem(position);
                        Log.e("onItemClick", "Erreur");
                        Intent intent = new Intent(getApplicationContext(), DetailEvenement.class);
                        intent.putExtra("itemSelect", (Serializable) eventSelect);
                        startActivity(intent);
                    }
                });
            } else {
                Toast.makeText(this, R.string.noEventToast, Toast.LENGTH_LONG).show();
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
