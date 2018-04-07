package me.readeveloper.alarmdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import me.readeveloper.alarmdroid.models.AlertItem;
import me.readeveloper.alarmdroid.utils.Auth;

public class AlertsListActivity extends AppCompatActivity {

    ArrayList<AlertItem> listDatos;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts_list);
        recycler = findViewById(R.id.Alerts);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recycler.setLayoutManager(new GridLayoutManager(this,3));

        listDatos = new ArrayList<AlertItem>();

        llenarLista();
        AdapterAllAlerts adapter = new AdapterAllAlerts(listDatos);
        recycler.setAdapter(adapter);
    }

    private void llenarLista() {
        //En esta funcion es donde se recibiran los datos y se guardaran en el constructor de la clase item
        listDatos.add(new AlertItem("Tipo","Mensaje","Zona","Fecha"));
        listDatos.add(new AlertItem("Incendio","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur, eos.", "Recusandae", "2018-03-06 03:47:00"));
        listDatos.add(new AlertItem("Gases contaminantes","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur, eos.", "Reprehenderit", "2018-03-06 03:47:00"));
        listDatos.add(new AlertItem("Alta temperatura","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur, eos.", "Reprehenderit", "2018-03-06 00:14:18"));
        listDatos.add(new AlertItem("Baja temperatura","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur, eos.", "Consequuntur", "2018-03-06 00:17:18"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.op1) {
            Toast.makeText(this, "Cuenta", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.op2) {
            Toast.makeText(this, "Información", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.op3) {
            Toast.makeText(this, "Cerrando sesión", Toast.LENGTH_SHORT).show();
            Auth.logout(this);
        } else {
            Toast.makeText(this, "Opcion no valida", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
