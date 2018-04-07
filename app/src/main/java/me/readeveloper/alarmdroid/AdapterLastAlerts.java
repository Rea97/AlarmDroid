package me.readeveloper.alarmdroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.readeveloper.alarmdroid.models.LastAlertItem;

/**
 * Created by HP Gamer on 07/03/2018.
 */

public class AdapterLastAlerts extends RecyclerView.Adapter<AdapterLastAlerts.ViewHolderDatos> {

    ArrayList<LastAlertItem> listAlerts;

    public AdapterLastAlerts(ArrayList<LastAlertItem> listAlerts) {
        this.listAlerts = listAlerts;
    }


    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        //Enlaza el adaptador con el archivo last_alerts_list
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.last_alerts_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.tipo.setText(listAlerts.get(position).getTipo());
        holder.fecha.setText(listAlerts.get(position).getFecha());
        holder.desc.setText(listAlerts.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return listAlerts.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        //Referencia a los componentes-datos que se mostraran en la lista
        TextView tipo, fecha, desc;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            tipo= (TextView) itemView.findViewById(R.id.LastAlertsTipo);
            fecha= (TextView) itemView.findViewById(R.id.LastAlertsDate);
            desc= (TextView) itemView.findViewById(R.id.LastAlertsDesc);
        }

        //public void asignarDatos(String datos) {
        //    dato.setText(datos);
        //}
    }
}
