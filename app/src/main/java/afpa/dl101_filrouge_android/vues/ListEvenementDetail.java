package afpa.dl101_filrouge_android.vues;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.objet.Evenement;
import afpa.dl101_filrouge_android.tacheAsynchrone.SelectEventFromDateAsync;
import afpa.dl101_filrouge_android.tacheAsynchrone.SelectEventFromIdAsync;

public class ListEvenementDetail extends AppCompatActivity {
    private Evenement evenement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_evenement);
        Intent intent = getIntent();
        intent.getIntExtra("itemSelectId", 0);
        SelectEventFromIdAsync selectEventAsync = new SelectEventFromIdAsync(this);
        try {
            evenement = selectEventAsync.execute(intent.getIntExtra("itemSelectId", 0)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent list = new Intent(this, ListMeteo.class);
        list.putExtra("locMeteo", evenement.getLocation());
        startActivity(list);
    }
}
