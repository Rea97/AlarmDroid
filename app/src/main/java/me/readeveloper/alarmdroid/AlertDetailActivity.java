package me.readeveloper.alarmdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.readeveloper.alarmdroid.models.Alert;
import me.readeveloper.alarmdroid.utils.Auth;
import me.readeveloper.alarmdroid.utils.HttpClient;

public class AlertDetailActivity extends AppCompatActivity {
    private TextView txtAlertType;
    private TextView txtAlertDate;
    private TextView txtAlertZone;
    private TextView txtAlertMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_detail);

        this.setTextViews();
        this.clearTextViews();

        Bundle bundle = getIntent().getBundleExtra("alertDetail");
        this.fetchAlert(bundle.getInt("id"));
    }

    private void setTextViews() {
        this.txtAlertType = findViewById(R.id.contentType);
        this.txtAlertDate = findViewById(R.id.contentDate);
        //this.txtAlertZone = findViewById(R.id.contentZone);
        this.txtAlertMessage = findViewById(R.id.contentMessage);
    }

    private void clearTextViews() {
        this.txtAlertType.setText("");
        this.txtAlertDate.setText("");
        //this.txtAlertZone.setText("");
        this.txtAlertMessage.setText("");
    }

    private void fetchAlert(int id) {
        HttpClient http = new HttpClient(
            String.format("https://alarmdroid.herokuapp.com/api/alerts/%d", id), this
        );

        http.setHeader("Authorization", "Bearer " + Auth.getApiTokenFromSharedPreferences(this))
            .setHeader("Accept", "application/json");

        http.get(new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonObject jsonResponse = parser.parse(response.toString()).getAsJsonObject();
                Alert alert = gson.fromJson(jsonResponse.get("data"), Alert.class);
                Log.d("alert", alert.getMessage());

                fillTextViews(alert);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlertDetailActivity.this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillTextViews(Alert alert) {
        this.txtAlertType.setText(alert.getType());
        this.txtAlertDate.setText(alert.getCreated_at());
        //this.txtAlertZone.setText(alert.getZone());
        this.txtAlertMessage.setText(alert.getMessage());
    }
}
