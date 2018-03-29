package me.readeveloper.alarmdroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP Gamer on 08/03/2018.
 */

public class AdapterAllAlerts extends RecyclerView.Adapter<AdapterAllAlerts.ViewHolderDatos> {
    ArrayList<AlertItem> Alerts;

    public AdapterAllAlerts(ArrayList<AlertItem> Alerts) {this.Alerts = Alerts;}

    @Override
    public AdapterAllAlerts.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_alerts_list,null,false);
        return new AdapterAllAlerts.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(AdapterAllAlerts.ViewHolderDatos holder, int position) {
        holder.tipo.setText(Alerts.get(position).getTipo());
        holder.mensaje.setText(Alerts.get(position).getMensaje());
        holder.zona.setText(Alerts.get(position).getZona());
        holder.fecha.setText(Alerts.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return Alerts.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tipo, mensaje, zona, fecha;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            tipo= (TextView) itemView.findViewById(R.id.Tipo);
            mensaje= (TextView) itemView.findViewById(R.id.Mensaje);
            zona= (TextView) itemView.findViewById(R.id.Zona);
            fecha= (TextView) itemView.findViewById(R.id.Fecha);
        }
    }
}
