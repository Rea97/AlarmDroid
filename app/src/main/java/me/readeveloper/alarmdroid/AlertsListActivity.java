package me.readeveloper.alarmdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import me.readeveloper.alarmdroid.models.Alert;
import me.readeveloper.alarmdroid.models.AlertItem;
import me.readeveloper.alarmdroid.models.AlertsDataObject;
import me.readeveloper.alarmdroid.models.LastAlertItem;
import me.readeveloper.alarmdroid.utils.Auth;
import me.readeveloper.alarmdroid.utils.HttpClient;

public class AlertsListActivity extends AppCompatActivity {

    ArrayList<AlertItem> listDatos;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!Auth.check(this)) {
            Auth.redirectToLogin(this);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts_list);
        recycler = findViewById(R.id.Alerts);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recycler.setLayoutManager(new GridLayoutManager(this,3));

        listDatos = new ArrayList<AlertItem>();

        this.fillList();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Auth.check(this)) {
            Auth.redirectToLogin(this);
        }
    }

    private void fillList() {
        HttpClient http = new HttpClient("https://alarmdroid.herokuapp.com/api/alerts", this);

        http.setHeader("Authorization", "Bearer " + Auth.getApiTokenFromSharedPreferences(this))
                .setHeader("Accept", "application/json");

        http.get(new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonObject jsonResponse = parser.parse(response.toString()).getAsJsonObject();
                AlertsDataObject data = gson.fromJson(jsonResponse, AlertsDataObject.class);

                for (Alert alert : data.getData()) {
                    listDatos.add(
                            new AlertItem(
                                    alert.getId(),
                                    alert.getType(),
                                    alert.getMessage(),
                                    alert.getZone(),
                                    alert.getCreated_at()
                            )
                    );
                }
                AdapterAllAlerts adapter = new AdapterAllAlerts(listDatos);
                recycler.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorResponse", "Error on request.", error);
            }
        });
    }

    public void searchAlerts(View view) {
        listDatos.clear();

        EditText searchField = findViewById(R.id.searchText);
        String search = searchField.getText().toString();

        HttpClient http = new HttpClient(
                "https://alarmdroid.herokuapp.com/api/alerts?search=" + search,
                this
        );

        http.setHeader("Authorization", "Bearer " + Auth.getApiTokenFromSharedPreferences(this))
                .setHeader("Accept", "application/json");

        http.get(new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonObject jsonResponse = parser.parse(response.toString()).getAsJsonObject();
                AlertsDataObject data = gson.fromJson(jsonResponse, AlertsDataObject.class);

                for (Alert alert : data.getData()) {
                    listDatos.add(
                            new AlertItem(
                                    alert.getId(),
                                    alert.getType(),
                                    alert.getMessage(),
                                    alert.getZone(),
                                    alert.getCreated_at()
                            )
                    );
                }
                AdapterAllAlerts adapter = new AdapterAllAlerts(listDatos);
                recycler.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorResponse", "Error on request.", error);
            }
        });
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
