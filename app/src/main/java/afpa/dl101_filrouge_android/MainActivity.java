package afpa.dl101_filrouge_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import afpa.dl101_filrouge_android.objet.Meteo;
import afpa.dl101_filrouge_android.tacheAsynchrone.RequeteMeteoAsync;
import afpa.dl101_filrouge_android.vues.AjouterEvenement;
import afpa.dl101_filrouge_android.vues.RechercherEvenement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_AjoutEvent:
                Intent intent1 = new Intent(this, AjouterEvenement.class);
                startActivity(intent1);
                break;
            case R.id.btn_ConsultEvent:
                Intent intent2 = new Intent(this, RechercherEvenement.class);
                startActivity(intent2);
                break;
            case R.id.btn_test:
                Meteo recMeteo = new Meteo("Caen", "20171031");
                RequeteMeteoAsync requeteMeteoAsync = new RequeteMeteoAsync(recMeteo);
                try {
                    Meteo meteoTest = requeteMeteoAsync.execute(recMeteo.getLocation()).get();
                    Toast.makeText(this, meteoTest.getLocation() + " "
                            + meteoTest.getTemperature() + "°C "
                            + meteoTest.getDescription(), Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // On teste l’Id de l’item cliqué et on déclenche une action
        switch (item.getItemId()) {
            case R.id.ajouterEvent:
                startActivity(new Intent(this, AjouterEvenement.class));
                return true;
            case R.id.rechercherEvent:
                startActivity(new Intent(this, RechercherEvenement.class));
                return true;
            case R.id.quitter:
                finish();
                return true;
            default:
                return false;
        }
    }
}
