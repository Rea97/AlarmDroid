package me.readeveloper.alarmdroid;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.readeveloper.alarmdroid.handlers.LastAlertOnClickHandler;
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
        Date alertDate = new Date();
        LastAlertOnClickHandler handler = new LastAlertOnClickHandler(this.listAlerts, position);
        holder.tipo.setOnClickListener(handler);
        holder.fecha.setOnClickListener(handler);
        holder.desc.setOnClickListener(handler);

        try {
            alertDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(listAlerts.get(position).getFecha());
        } catch (Exception e) {
            Log.e("DateParseError", "Error al parsear fecha", e);
        }
        holder.tipo.setText(listAlerts.get(position).getTipo());
        holder.fecha.setReferenceTime(alertDate.getTime());
        holder.desc.setText(listAlerts.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return listAlerts.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        //Referencia a los componentes-datos que se mostraran en la lista
        TextView tipo, desc;
        RelativeTimeTextView fecha;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            tipo= (TextView) itemView.findViewById(R.id.LastAlertsTipo);
            fecha= (RelativeTimeTextView) itemView.findViewById(R.id.LastAlertsDate);
            desc= (TextView) itemView.findViewById(R.id.LastAlertsDesc);
        }

        //public void asignarDatos(String datos) {
        //    dato.setText(datos);
        //}
    }
}
