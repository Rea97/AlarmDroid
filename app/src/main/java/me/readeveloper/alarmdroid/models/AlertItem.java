package me.readeveloper.alarmdroid.models;

/**
 * Created by HP Gamer on 08/03/2018.
 */

public class AlertItem {
    private int id;
    private String tipo;
    private String mensaje;
    private String zona;
    private String fecha;

    public AlertItem(int id, String tipo, String mensaje, String zona, String fecha) {
        this.id = id;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.zona = zona;
        this.fecha = fecha;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
