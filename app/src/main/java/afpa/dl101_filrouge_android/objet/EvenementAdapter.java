package afpa.dl101_filrouge_android.objet;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import afpa.dl101_filrouge_android.R;

/**
 * Created by DL101 on 26/10/2017.
 */

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

        TextView editTitre = (TextView) layoutItem.findViewById(R.id.editTitre);
        TextView dateDebut = (TextView) layoutItem.findViewById(R.id.dateDebutDisplay);
        TextView dateFin = (TextView) layoutItem.findViewById(R.id.dateFinDisplay);
        TextView editDesc = (TextView) layoutItem.findViewById(R.id.editDescription);

        editTitre.setText(vListEvenement.get(position).getTitre());
        dateDebut.setText(String.valueOf(vListEvenement.get(position).getDateIntDebut()));
        dateFin.setText(String.valueOf(vListEvenement.get(position).getDateIntFin()));
        editDesc.setText(vListEvenement.get(position).getDescription());

        return layoutItem;
    }
}
