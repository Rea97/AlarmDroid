package me.readeveloper.alarmdroid.models;

/**
 * Created by HP Gamer on 08/03/2018.
 */

public class AlertItem extends Model {
    private String tipo;
    private String mensaje;
    private String zona;
    private String fecha;

    public AlertItem(String tipo, String mensaje, String zona, String fecha) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.zona = zona;
        this.fecha = fecha;
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
