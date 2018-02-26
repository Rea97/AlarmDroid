package me.readeveloper.alarmdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
