package afpa.dl101_filrouge_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import afpa.dl101_filrouge_android.database.EvenementManager;
import afpa.dl101_filrouge_android.vues.AjouterEvenement;
import afpa.dl101_filrouge_android.vues.ListEvenement;

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
        switch (view.getId()) {
            case R.id.btn_AjoutEvent:
                Intent intent1 = new Intent(this, AjouterEvenement.class);
                startActivity(intent1);
                break;
            case R.id.btn_ConsultEvent:
                Intent intent2 = new Intent(this, ListEvenement.class);
                startActivity(intent2);
                break;
        }
    }
}
