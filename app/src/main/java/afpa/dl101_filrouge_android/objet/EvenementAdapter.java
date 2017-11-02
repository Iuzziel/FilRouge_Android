package afpa.dl101_filrouge_android.objet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.metier.ToolBox;
import afpa.dl101_filrouge_android.tacheAsynchrone.RequeteMeteoAsync;


public class EvenementAdapter extends BaseAdapter {
    private List<Evenement> vListEvenement;
    private Context mContext;
    private LayoutInflater mInflater;

    public EvenementAdapter(Context mContext, List<Evenement> vListEvenement) {
        this.mContext = mContext;
        this.vListEvenement = vListEvenement;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return vListEvenement.size();
    }

    @Override
    public Object getItem(int position) {
        return vListEvenement.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;
        if (convertView == null) {
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.activity_consulter_evenement, parent, false);
        } else {
            layoutItem = (ConstraintLayout) convertView;
        }

        TextView editTitre = layoutItem.findViewById(R.id.editTitre);
        TextView dateDebut = layoutItem.findViewById(R.id.dateDebutDisplay);
        TextView dateFin = layoutItem.findViewById(R.id.dateFinDisplay);
        TextView editEmpl = layoutItem.findViewById(R.id.editEmpl);
        TextView editDesc = layoutItem.findViewById(R.id.editDescription);
        ImageView imgMeteo = layoutItem.findViewById(R.id.imgMeteo);

        RequeteMeteoAsync requeteMeteoAsync = new RequeteMeteoAsync(new Meteo(vListEvenement.get(position).getLocation(),
                String.valueOf(vListEvenement.get(position).getDateLongDebut())));

        Meteo meteo = new Meteo("no_wifi");
        if (ToolBox.isNetworkAvailable(mContext)) {
            try {
                meteo = requeteMeteoAsync.execute(vListEvenement.get(position).getLocation()).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        imgMeteo.setImageResource(mContext.getResources().getIdentifier("icn_" + meteo.getIcone(), "mipmap", mContext.getPackageName()));

        editTitre.setText(vListEvenement.get(position).getTitre());
        dateDebut.setText(ToolBox.formatDate(String.valueOf(vListEvenement.get(position).getDateIntDebut())));
        dateFin.setText(ToolBox.formatDate(String.valueOf(vListEvenement.get(position).getDateIntFin())));
        editEmpl.setText(vListEvenement.get(position).getLocation());
        editDesc.setText(vListEvenement.get(position).getDescription());

        return layoutItem;
    }

}
