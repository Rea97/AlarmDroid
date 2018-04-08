package me.readeveloper.alarmdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import me.readeveloper.alarmdroid.utils.Auth;
import me.readeveloper.alarmdroid.utils.HttpClient;

public class DriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
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

    public void setStatus(View view) {
        String status;
        switch (view.getId()) {
            case R.id.ControlAvanzar:
                status = "adelante";
                break;
            case R.id.ControlAtras:
                status = "atras";
                break;
            case R.id.ControlDer:
                status = "derecha";
                break;
            case R.id.ControlIzq:
                status = "izquierda";
                break;
            case R.id.ControlDetener:
                status = "detenido";
                break;
            default:
                status = "detenido";
                break;
        }

        this.sendStatus(status);
    }

    private void sendStatus(String status) {
        HttpClient http = new HttpClient("https://alarmdroid.herokuapp.com/api/users/robots/status", this);
        http.setHeader("Authorization", "Bearer " + Auth.getApiTokenFromSharedPreferences(this))
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .setHeader("Accept", "application/json")
                .setParameter("status", status);

        http.put(new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Toast.makeText(
                        DriverActivity.this,
                        "Estatus del robot ha cambiado.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorResponse", "Error on request.", error);
            }
        });
    }
}
