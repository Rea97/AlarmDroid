package me.readeveloper.alarmdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import me.readeveloper.alarmdroid.utils.Auth;

public class AlertDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op1:
                Toast.makeText(this, "Cuenta", Toast.LENGTH_SHORT).show();
                break;
            case R.id.op2:
                Toast.makeText(this, "Información", Toast.LENGTH_SHORT).show();
                break;
            case R.id.op3:
                Toast.makeText(this, "Cerrando sesión", Toast.LENGTH_SHORT).show();
                Auth.logout(this);
                break;
            default:
                Toast.makeText(this, "Opcion no valida", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
