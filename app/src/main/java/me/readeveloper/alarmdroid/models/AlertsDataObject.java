package me.readeveloper.alarmdroid.models;

import java.util.List;

public class AlertsDataObject {
    private List<Alert> data;

    public AlertsDataObject(List<Alert> data) {
        this.data = data;
    }

    public List<Alert> getData() {
        return this.data;
    }

    public void setData(List<Alert> data) {
        this.data = data;
    }
}
