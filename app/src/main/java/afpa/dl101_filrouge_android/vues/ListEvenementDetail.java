package afpa.dl101_filrouge_android.vues;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.metier.ToolBox;

public class ListEvenementDetail extends AppCompatActivity {
    private int rDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_evenement);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dateConsultDisplay:
                DialogFragment newFragment = new RechercherEvenement.DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
                break;
            case R.id.btn_recherche:
                if (rDate > 0) {
                    Intent intent = new Intent(this, ListEvenement.class);
                    intent.putExtra("rDate", rDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.err_incomplet, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_annuler:
                System.exit(0);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void onDateSet(int year, int monthOfYear, int dayOfMonth) {
        TextView textView = (TextView) findViewById(R.id.dateConsultDisplay);
        textView.setText(ToolBox.padLeft(String.valueOf(dayOfMonth))
                + "/" + ToolBox.padLeft(String.valueOf(monthOfYear + 1))
                + "/" + year);
        rDate = Integer.valueOf(year
                + ToolBox.padLeft(String.valueOf(monthOfYear + 1))
                + ToolBox.padLeft(String.valueOf(dayOfMonth)));
    }

    // Date picker fragment
    public static class DatePickerFragment extends DialogFragment
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
            ((RechercherEvenement) getActivity()).onDateSet(year, monthOfYear, dayOfMonth);
        }
    }
}
