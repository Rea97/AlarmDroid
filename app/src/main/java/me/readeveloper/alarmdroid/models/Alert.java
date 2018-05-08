package me.readeveloper.alarmdroid.models;

public class Alert extends Model {
    private int id;

    private String type;
    private String message;
    private String zone;
    private String created_at;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getZone() {
        return this.zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
