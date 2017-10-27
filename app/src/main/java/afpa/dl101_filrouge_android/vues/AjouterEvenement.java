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
import afpa.dl101_filrouge_android.metier.ToolBox;
import afpa.dl101_filrouge_android.objet.Evenement;
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

        mDateDebut = (TextView) findViewById(R.id.dateConsultDisplay);
        mDateFin = (TextView) findViewById(R.id.dateFinDisplay);
    }

    public void changerLaDate(View view) {
        if (view.getId() == R.id.dateConsultDisplay) {
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

    private void updateDate(String quand) {// Mois sur une base 0
        if (quand.equals("debut")) {
            mDateDebut.setText(new StringBuilder()
                    .append(dDay).append("/").append(dMonth + 1).append("/")
                    .append(dYear));
        } else if (quand.equals("fin")) {
            mDateFin.setText(new StringBuilder()
                    .append(fDay).append("/").append(fMonth + 1).append("/")
                    .append(fYear));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_EditEvent:
                ajouterEvent();
                break;
            case R.id.btn_DelEvent:
                System.exit(0);
                break;
            default:
                Toast.makeText(this, "Erreur onClick", Toast.LENGTH_SHORT).show();
        }
    }

    private void ajouterEvent() {
        EditText titre = (EditText) findViewById(R.id.editTitre);
        EditText description = (EditText) findViewById(R.id.editDescription);

        if ((titre.getText().toString().equals("")
                || description.getText().toString().equals("")
                || mDateDebut.getText().toString().equals("")
                || mDateFin.getText().toString().equals(""))
                && Integer.valueOf(mDateFin.getText().toString()) > Integer.valueOf(mDateDebut.getText().toString())) {
            // Si l'ajout est incomplet:
            Toast.makeText(this, R.string.err_incomplet, Toast.LENGTH_LONG).show();
        } else {
            // Si l'ajout est comlet:
            InsertEventAsynchrone insertEventAsynchrone = new InsertEventAsynchrone(this);
            try {// Les mois sont sur une base 0 avec le datePicker
                long tmp = insertEventAsynchrone.execute(new Evenement(titre.getText().toString(),
                        description.getText().toString(),
                        Integer.valueOf(String.valueOf(dYear)
                                + ToolBox.padLeft(String.valueOf(dMonth + 1))
                                + ToolBox.padLeft(String.valueOf(dDay))
                        ),
                        Integer.valueOf(String.valueOf(fYear)
                                + ToolBox.padLeft(String.valueOf(fMonth + 1))
                                + ToolBox.padLeft(String.valueOf(fDay))
                        ))
                ).get();
                Toast.makeText(this, R.string.insertReussi, Toast.LENGTH_LONG).show();
                Log.d("AsyncIns", "Insert réussi");
                System.exit(0);
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
