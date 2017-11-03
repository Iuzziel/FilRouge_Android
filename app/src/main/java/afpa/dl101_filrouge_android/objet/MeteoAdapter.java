package afpa.dl101_filrouge_android.objet;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import afpa.dl101_filrouge_android.R;
import afpa.dl101_filrouge_android.metier.ItemDetailMeteo;

public class MeteoAdapter extends BaseAdapter {
    private ArrayList<ItemDetailMeteo> tMeteo;
    private Context mContext;
    private LayoutInflater mInflater;

    public MeteoAdapter(Context mContext, ArrayList<ItemDetailMeteo> tMeteo) {
        this.mContext = mContext;
        this.tMeteo = tMeteo;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return tMeteo.size();
    }

    @Override
    public Object getItem(int position) {
        return tMeteo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;
        if (convertView == null) {
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.list_element_meteo, parent, false);
        } else {
            layoutItem = (ConstraintLayout) convertView;
        }

        ImageView imgMeteo = layoutItem.findViewById(R.id.imgMeteo);
        TextView date = layoutItem.findViewById(R.id.tv_date_meteo);

        date.setText(tMeteo.get(position).dateHeureConcernee);
        imgMeteo.setImageResource(mContext.getResources().getIdentifier("icn_" + tMeteo.get(position).iconeMeteo, "mipmap", mContext.getPackageName()));

        return layoutItem;
    }

}
