package afpa.dl101_filrouge_android.vues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.metier.ItemDetailMeteo;
import afpa.dl101_filrouge_android.objet.Meteo;
import afpa.dl101_filrouge_android.objet.MeteoAdapter;
import afpa.dl101_filrouge_android.tacheAsynchrone.ReqMeteoForecast;

public class ListMeteo extends AppCompatActivity {
    private Meteo meteo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_meteo);
        meteo = new Meteo("no_wifi");
        Intent thisIntent = getIntent();

        this.meteo.setLocation(thisIntent.getStringExtra("locMeteo"));
        try {
            initControl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initControl() {
        Map<String, String> map = new HashMap<>();
        ReqMeteoForecast reqMeteoForecast = new ReqMeteoForecast(meteo);
        try {
            map = reqMeteoForecast.execute(meteo).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ArrayList<ItemDetailMeteo> listEvent = getAListOfMeteo(map);
            final MeteoAdapter adapter = new MeteoAdapter(this, listEvent);
            ListView list = (ListView) findViewById(R.id.listEvent);
            list.setAdapter(adapter);
            /*
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object eventSelect = adapter.getItem(position);
                    Log.e("onItemClick", "Erreur");
                    Intent intent = new Intent(getApplicationContext(), ListEvenementDetail.class);
                    intent.putExtra("itemSelect", (Serializable) eventSelect);
                    startActivity(intent);
                }
            });
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ItemDetailMeteo> getAListOfMeteo(Map<String, String> map) {
        ArrayList<ItemDetailMeteo> listEvent = new ArrayList<>();
        ItemDetailMeteo itemDetailMeteo;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            itemDetailMeteo = new ItemDetailMeteo(entry.getKey(), entry.getValue());
            listEvent.add(itemDetailMeteo);
        }
        ComparatorIDM cIdm = new ComparatorIDM();
        java.util.Collections.sort(listEvent, cIdm);
        return listEvent;
    }

    public class ComparatorIDM implements Comparator<ItemDetailMeteo> {
        @Override
        public int compare(ItemDetailMeteo o1, ItemDetailMeteo o2) {
            return o1.dateHeureConcernee.compareTo(o2.dateHeureConcernee);
        }
    }
}
