package afpa.dl101_filrouge_android.vues;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.objets.Evenement;
import afpa.dl101_filrouge_android.tacheAsynchrone.InsertEventAsynchrone;

public class AjouterEvenement extends AppCompatActivity {
    private TextView mDateDebut;
    private int dYear;
    private int dMonth;
    private int dDay;
    private TextView mDateFin;
    private int fYear;
    private int fMonth;
    private int fDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_evenement);

        mDateDebut = (TextView) findViewById(R.id.dateDebutDisplay);
        mDateFin = (TextView) findViewById(R.id.dateFinDisplay);
    }

    public void changerLaDate(View view) {
        if (view.getId() == R.id.dateDebutDisplay) {
            DialogFragment newFragment = new DatePickerDebutFragment();
            newFragment.show(getFragmentManager(), "DatePicker");
        } else if (view.getId() == R.id.dateFinDisplay) {
            DialogFragment newFragment = new DatePickerFinFragment();
            newFragment.show(getFragmentManager(), "DatePicker");
        }
    }

    public void onDateSet(String string, int year, int monthOfYear, int dayOfMonth) {
        if (string.equals("Debut")) {
            dYear = year;
            dMonth = monthOfYear;
            dDay = dayOfMonth;
            updateDate("debut");
        } else if (string.equals("Fin")) {
            fYear = year;
            fMonth = monthOfYear;
            fDay = dayOfMonth;
            updateDate("fin");
        }
    }

    private void updateDate(String quand) {
        if (quand.equals("debut")) {
            mDateDebut.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(dDay).append("/").append(dMonth + 1).append("/")
                    .append(dYear));
        } else if (quand.equals("fin")) {
            mDateFin.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(fDay).append("/").append(fMonth + 1).append("/")
                    .append(fYear));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_AjoutEvent:
                ajouterEvent();
                break;
            case R.id.btn_Annuler:
                System.exit(0);
                break;
            default:
                Toast.makeText(this, "Erreur onClick", Toast.LENGTH_SHORT).show();
        }
    }

    private void ajouterEvent() {
        EditText titre = (EditText) findViewById(R.id.editTitre);
        EditText description = (EditText) findViewById(R.id.editDescription);
        // TODO Controle sur les dates ex: debut apres la fin?
        if (titre.getText().toString().equals("")
                || description.getText().toString().equals("")
                || mDateDebut.getText().toString().equals("")
                || mDateFin.getText().toString().equals("")) {
            // Si l'ajout est incomplet:
            Toast.makeText(this, "L'ajout est incomplet", Toast.LENGTH_LONG).show();
        } else {
            // Si l'ajout est comlet:
            InsertEventAsynchrone insertEventAsynchrone = new InsertEventAsynchrone();
            try {
                long tmp = insertEventAsynchrone.execute(new Evenement(titre.getText().toString(),
                        description.getText().toString(),
                        Integer.valueOf(String.valueOf(fDay)
                                + String.valueOf(fMonth)
                                + String.valueOf(fYear)
                        ),
                        Integer.valueOf(String.valueOf(fDay)
                                + String.valueOf(fMonth)
                                + String.valueOf(fYear)
                        ))
                ).get();
                Toast.makeText(this, "Insert réussi id:" + tmp, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("AjoutEvenement", e.getLocalizedMessage());
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /*Date picker en popup*/
    public static class DatePickerDebutFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Set the current date in the DatePickerDebutFragment
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        // Callback to DatePickerActivity.onDateSet() to update the UI
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ((AjouterEvenement) getActivity()).onDateSet("Debut", year, monthOfYear, dayOfMonth);
        }
    }

    /*Date picker en popup*/
    public static class DatePickerFinFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Set the current date in the DatePickerDebutFragment
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        // Callback to DatePickerActivity.onDateSet() to update the UI
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ((AjouterEvenement) getActivity()).onDateSet("Fin", year, monthOfYear, dayOfMonth);
        }
    }
}
