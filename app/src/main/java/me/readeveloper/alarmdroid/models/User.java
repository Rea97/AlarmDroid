package me.readeveloper.alarmdroid.models;

public class User {
    private int id;
    private int robot_id;
    private String name;
    private String email;
    private String created_at;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRobot_id() {
        return this.robot_id;
    }

    public void setRobot_id(int robot_id) {
        this.robot_id = robot_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
