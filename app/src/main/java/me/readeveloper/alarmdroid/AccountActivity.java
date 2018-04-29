package me.readeveloper.alarmdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.readeveloper.alarmdroid.models.Robot;
import me.readeveloper.alarmdroid.utils.Auth;
import me.readeveloper.alarmdroid.utils.HttpClient;

public class AccountActivity extends AppCompatActivity {
    private final String API_BASE_ENDPOINT = "https://alarmdroid.herokuapp.com/api";
    private final String API_ACCOUNT_ENDPOINT = API_BASE_ENDPOINT + "/robots";
    private TextView txtUserName;
    private TextView txtUserEmail;
    private TextView txtRobotZone;
    private TextView txtRobotModel;
    private TextView txtRobotStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        this.setTextView();
        this.fetchAccountInfo();
    }

    private void fetchAccountInfo() {
        String robotId = Integer.toString(Auth.getRobotIdFromSharedPreferences(this));
        String apiToken = Auth.getApiTokenFromSharedPreferences(this);
        String url = API_ACCOUNT_ENDPOINT + "/" + robotId + "?api_token=" + apiToken;
        HttpClient http = new HttpClient(url, this);

        http.get(new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(response.toString()).getAsJsonObject();
                Robot robot = gson.fromJson(json.get("data"), Robot.class);
                fillTextView(robot);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AccountActivity.this, "No se ha podido recuperar los datos de tu cuenta", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setTextView() {
        this.txtUserName = (TextView) findViewById(R.id.txtUserName);
        this.txtUserEmail = (TextView) findViewById(R.id.txtUserEmail);
        this.txtRobotModel = (TextView) findViewById(R.id.txtRobotModel);
        this.txtRobotZone = (TextView) findViewById(R.id.txtRobotZone);
        this.txtRobotStatus = (TextView) findViewById(R.id.txtRobotStatus);
    }

    private void fillTextView(Robot robot) {
        this.txtUserName.setText(robot.getUser().getName());
        this.txtUserEmail.setText(robot.getUser().getEmail());
        this.txtRobotZone.setText(robot.getZone());
        this.txtRobotModel.setText(robot.getModel());
        this.txtRobotStatus.setText(robot.getStatus());
    }

    public void driveRobot(View view) {
        startActivity(new Intent(this, DriverActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appmenu, menu);
        menu.removeItem(R.id.op1);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.op2) {
            Toast.makeText(this, "Información", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.op3) {
            Toast.makeText(this, "Cerrando sesión", Toast.LENGTH_SHORT).show();
            Auth.logout(this);
        } else {
            Toast.makeText(this, "Opcion no valida", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
