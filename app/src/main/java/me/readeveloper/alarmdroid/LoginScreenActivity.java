package me.readeveloper.alarmdroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;
import java.util.HashMap;

import me.readeveloper.alarmdroid.models.ApiToken;

public class LoginScreenActivity extends AppCompatActivity {
    private final String SP_FILENAME = "alarmdroid.xml";
    private final String LOGIN_URL = "https://alarmdroid.herokuapp.com/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
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
        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);
        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, LOGIN_URL, new LoginResponseHandler(), new LoginErrorHandler()
        ) {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Accept","application/json");
                return params;
            }
        };

        queue.add(stringRequest);
    }

    private void dispatchMainActivityIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void storeApiToken(String apiToken) {
        SharedPreferences sharedPreferences = getSharedPreferences(SP_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("api_token", apiToken);

        editor.apply();
    }

    private class LoginResponseHandler implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            Gson gson = new Gson();

            ApiToken apiToken = gson.fromJson(response, ApiToken.class);

            if (apiToken.toString() != null) {
                storeApiToken(apiToken.toString());
                dispatchMainActivityIntent();
            }
        }
    }

    private class LoginErrorHandler implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("ErrorResponse", "Error on request.", error);
            Toast.makeText(
                    LoginScreenActivity.this,
                    "Credenciales inválidas.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
