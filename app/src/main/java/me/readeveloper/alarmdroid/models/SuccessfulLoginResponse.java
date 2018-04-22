package me.readeveloper.alarmdroid.models;

public class SuccessfulLoginResponse extends Model {
    private String api_token = null;
    private int id;

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

    public boolean isValid() {
        return this.api_token != null && this.id > 0;
    }

    @Override
    public String toString() {
        return this.getApi_token();
    }
}
