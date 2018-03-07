package me.readeveloper.alarmdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import me.readeveloper.alarmdroid.models.ApiToken;

public class LoginScreenActivity extends AppCompatActivity {

    private String defaultEmail = "test@alarmdroid.com";
    private String defaultPassword = "12345";
    private String url = "https://alarmdroid.herokuapp.com/api/login";
    private ApiToken apiToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void login(View view) {
        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);
        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, this.url, new LoginResponseHandler(), new LoginErrorHandler()
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

    private class LoginResponseHandler implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            Gson gson = new Gson();

            apiToken = gson.fromJson(response, ApiToken.class);

            if (apiToken.toString() != null) {
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
