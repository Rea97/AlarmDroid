package me.readeveloper.alarmdroid.handlers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import me.readeveloper.alarmdroid.LoginScreenActivity;

public class LogoutHandler {
    private final String SP_FILENAME = "alarmdroid.xml";
    private String logoutErrorMessage = "Ha ocurrido un problema al cerrar sesión.";
    private Context context;

    public LogoutHandler(Context context) {
        this.context = context;
    }

    public void logout() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(
                SP_FILENAME, Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("api_token");

        if (editor.commit()) {
            this.dispatchLoginActivityIntent();
            return;
        }

        Toast.makeText(this.context, this.logoutErrorMessage, Toast.LENGTH_LONG).show();
    }

    private void dispatchLoginActivityIntent() {
        Intent intent = new Intent(this.context, LoginScreenActivity.class);
        this.context.startActivity(intent);
    }
}
