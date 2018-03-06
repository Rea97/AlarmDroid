package me.readeveloper.alarmdroid.models;

public class ApiToken {
    private String api_token = null;

    public String getApi_token() {
        return this.api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    @Override
    public String toString() {
        return this.getApi_token();
    }
}
