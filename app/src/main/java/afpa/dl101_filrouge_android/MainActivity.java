package afpa.dl101_filrouge_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import afpa.dl101_filrouge_android.objet.Meteo;
import afpa.dl101_filrouge_android.tacheAsynchrone.RequeteMeteoAsync;
import afpa.dl101_filrouge_android.vues.AjouterEvenement;
import afpa.dl101_filrouge_android.vues.ConsulterEvenement;

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
                Intent intent2 = new Intent(this, ConsulterEvenement.class);
                startActivity(intent2);
                break;
            case R.id.btn_test:
                RequeteMeteoAsync requeteMeteoAsync = new RequeteMeteoAsync(
                        new Meteo("", 0d, 0d, 0d, 0d, 0d));
                try {
                    Meteo meteoTest = requeteMeteoAsync.execute("Caen,fr").get();
                    Toast.makeText(this, meteoTest.toString(), Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
