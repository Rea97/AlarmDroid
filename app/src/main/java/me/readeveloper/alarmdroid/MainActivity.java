package me.readeveloper.alarmdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<LastAlertItem> listDatos;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.lastAlerts);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recycler.setLayoutManager(new GridLayoutManager(this,3));

        listDatos = new ArrayList<LastAlertItem>();

        llenarLista();
        AdapterLastAlerts adapter = new AdapterLastAlerts(listDatos);
        recycler.setAdapter(adapter);
    }

    private void llenarLista() {
        //En esta funcion es donde se recibiran los datos y se guardaran en el constructor de la clase item
        listDatos.add(new LastAlertItem("Incendio", "2018-03-06 03:47:00", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur, eos."));
        listDatos.add(new LastAlertItem("Gases contaminantes", "2018-03-06 03:47:00", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur, eos."));
        listDatos.add(new LastAlertItem("Alta temperatura", "2018-03-06 00:14:18", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur, eos."));
        listDatos.add(new LastAlertItem("Baja temperatura", "2018-03-06 00:17:18", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur, eos."));
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
            Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Opcion no valida", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlertsList(View view) {
        Intent intent = new Intent(this, AlertsListActivity.class);
        startActivity(intent);
    }

    public void showRobotVision(View view) {
        Intent intent = new Intent(this, DriverActivity.class);
        startActivity(intent);
    }
}
