package me.readeveloper.alarmdroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import me.readeveloper.alarmdroid.models.SuccessfulLoginResponse;
import me.readeveloper.alarmdroid.utils.HttpClient;

public class LoginScreenActivity extends AppCompatActivity {
    private final String SP_FILENAME = "alarmdroid.xml";
    private final String LOGIN_URL = "https://alarmdroid.herokuapp.com/api/login";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        this.progressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(SP_FILENAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("api_token", null);
        if (token != null) {
            this.dispatchMainActivityIntent();
        }
    }

    public void login(View view) {
        this.progressBar.setVisibility(View.VISIBLE);
        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);
        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();

        HttpClient http = new HttpClient(this.LOGIN_URL, this);
        http.setHeader("Content-Type", "application/x-www-form-urlencoded")
                .setHeader("Accept", "application/json")
                .setParameter("email", email)
                .setParameter("password", password);

        http.post(new LoginResponseHandler(), new LoginErrorHandler());
    }

    private void dispatchMainActivityIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void storeAuthData(String apiToken, int authId, int robotId) {
        SharedPreferences sharedPreferences = getSharedPreferences(SP_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("api_token", apiToken);
        editor.putInt("auth_id", authId);
        editor.putInt("robot_id", robotId);

        editor.apply();
    }

    private class LoginResponseHandler implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            Gson gson = new Gson();

            SuccessfulLoginResponse loginResponse = gson.fromJson(response, SuccessfulLoginResponse.class);

            if (loginResponse.isValid()) {
                progressBar.setVisibility(View.INVISIBLE);
                storeAuthData(loginResponse.getApi_token(), loginResponse.getId(), loginResponse.getRobot_id());
                dispatchMainActivityIntent();
            }
        }
    }

    private class LoginErrorHandler implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("ErrorResponse", "Error on request.", error);
            // FIXME: parse error and get validation message, then display it
            Toast.makeText(
                    LoginScreenActivity.this,
                    "Credenciales inválidas.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
