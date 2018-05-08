package me.readeveloper.alarmdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!Auth.check(this)) {
            Auth.redirectToLogin(this);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts_list);
        progressBar = (ProgressBar) findViewById(R.id.alertsListProgressBar);
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
                progressBar.setVisibility(View.GONE);
                recycler.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
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
}
