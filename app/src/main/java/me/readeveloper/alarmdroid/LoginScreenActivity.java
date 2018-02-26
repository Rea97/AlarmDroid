package me.readeveloper.alarmdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity {

    private String defaultEmail = "oziel@test.com";
    private String defaultPassword = "secret";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void login(View view) {
        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.equals(this.defaultEmail) && password.equals(this.defaultPassword)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return;
        }
        Toast.makeText(this, "Credenciales inválidas.", Toast.LENGTH_LONG).show();
    }
}
