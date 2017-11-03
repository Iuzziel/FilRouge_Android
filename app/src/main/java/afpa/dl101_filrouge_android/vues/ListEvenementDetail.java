package afpa.dl101_filrouge_android.vues;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.metier.ToolBox;
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

        TextView editTitre = (TextView) findViewById(R.id.editTitre);
        TextView dateDebut = (TextView) findViewById(R.id.dateDebutDisplay);
        TextView dateFin = (TextView) findViewById(R.id.dateFinDisplay);
        TextView editEmpl = (TextView) findViewById(R.id.editEmpl);
        TextView editDesc = (TextView) findViewById(R.id.editDescription);

        editTitre.setText(evenement.getTitre());
        dateDebut.setText(ToolBox.formatDate(String.valueOf(evenement.getDateIntDebut())));
        dateFin.setText(ToolBox.formatDate(String.valueOf(evenement.getDateIntFin())));
        editEmpl.setText(evenement.getLocation());
        editDesc.setText(evenement.getDescription());

        Intent list = new Intent(this, ListMeteo.class);
        list.putExtra("locMeteo", evenement.getLocation());
        startActivity(list);
    }
}
