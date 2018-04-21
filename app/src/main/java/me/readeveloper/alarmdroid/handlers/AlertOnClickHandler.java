package me.readeveloper.alarmdroid.handlers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import me.readeveloper.alarmdroid.AlertDetailActivity;
import me.readeveloper.alarmdroid.models.AlertItem;

public class AlertOnClickHandler implements View.OnClickListener {
    private ArrayList<AlertItem> alerts;
    private int position;

    public AlertOnClickHandler(ArrayList<AlertItem> alerts, int position) {
        this.alerts = alerts;
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AlertDetailActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("id", this.alerts.get(this.position).getId());
        intent.putExtra("alertDetail", bundle);

        view.getContext().startActivity(intent);
    }
}
