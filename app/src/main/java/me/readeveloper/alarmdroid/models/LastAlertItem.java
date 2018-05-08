package me.readeveloper.alarmdroid.models;

/**
 * Created by HP Gamer on 07/03/2018.
 */

public class LastAlertItem {
    private int id;
    private String tipo;
    private String fecha;
    private String desc;

    public LastAlertItem(int id, String tipo, String fecha, String desc) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
