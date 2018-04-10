package me.readeveloper.alarmdroid.handlers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import me.readeveloper.alarmdroid.AlertDetailActivity;
import me.readeveloper.alarmdroid.models.AlertItem;
import me.readeveloper.alarmdroid.models.LastAlertItem;

public class LastAlertOnClickHandler implements View.OnClickListener {
    private ArrayList<LastAlertItem> alerts;
    private int position;

    public LastAlertOnClickHandler(ArrayList<LastAlertItem> alerts, int position) {
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
