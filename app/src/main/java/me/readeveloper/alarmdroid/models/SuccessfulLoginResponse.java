package me.readeveloper.alarmdroid.models;

public class SuccessfulLoginResponse extends Model {
    private String api_token = null;
    private int id;
    private int robot_id;

    public String getApi_token() {
        return this.api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

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

    public boolean isValid() {
        return this.api_token != null && this.id > 0;
    }

    @Override
    public String toString() {
        return this.getApi_token();
    }
}
