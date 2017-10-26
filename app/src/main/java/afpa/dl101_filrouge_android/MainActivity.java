package afpa.dl101_filrouge_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import afpa.dl101_filrouge_android.database.EvenementManager;
import afpa.dl101_filrouge_android.vues.AjouterEvenement;

public class MainActivity extends AppCompatActivity {
    public static Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = this;
        EvenementManager em = new EvenementManager(this);
        em.open();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_calendrier) {
            Intent intent = new Intent(this, AjouterEvenement.class);
            startActivity(intent);
        }
    }
}
