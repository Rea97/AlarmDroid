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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pusher.pushnotifications.PushNotifications;

import java.util.ArrayList;

import me.readeveloper.alarmdroid.models.Alert;
import me.readeveloper.alarmdroid.models.AlertsDataObject;
import me.readeveloper.alarmdroid.models.LastAlertItem;
import me.readeveloper.alarmdroid.utils.Auth;
import me.readeveloper.alarmdroid.utils.HttpClient;

public class MainActivity extends AppCompatActivity {

    ArrayList<LastAlertItem> listDatos;
    RecyclerView recycler;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.lastAlertsProgressBar);
        recycler = findViewById(R.id.lastAlerts);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        int authId = Auth.getAuthIdFromSharedPreferences(this);
        //recycler.setLayoutManager(new GridLayoutManager(this,3));
        listDatos = new ArrayList<LastAlertItem>();

        this.fillList();

        PushNotifications.start(getApplicationContext(), "ec0303c1-5d35-4a2e-965e-b8ab64f90516");
        PushNotifications.subscribe(String.format("user-alerts-%d", authId));
    }

    private void fillList() {
        HttpClient http = new HttpClient("https://alarmdroid.herokuapp.com/api/alerts?limit=8", this);

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
                            new LastAlertItem(
                                    alert.getId(), alert.getType(), alert.getCreated_at(), alert.getMessage()
                            )
                    );
                }
                AdapterLastAlerts adapter = new AdapterLastAlerts(listDatos);
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

    @Override
    protected void onResume() {
        super.onResume();

        if (!Auth.check(this)) {
            Auth.redirectToLogin(this);
        }
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
            startActivity(new Intent(this, AccountActivity.class));
            Toast.makeText(this, "Cuenta", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.op2) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id==R.id.op3) {
            Toast.makeText(this, "Cerrando sesión", Toast.LENGTH_SHORT).show();
            Auth.logout(this);
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
