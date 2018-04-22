package me.readeveloper.alarmdroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import me.readeveloper.alarmdroid.LoginScreenActivity;

public class Auth {
    private final static String SP_FILENAME = "alarmdroid.xml";
    private final static String LOGOUT_ERROR_MESSAGE = "Ha ocurrido un problema al cerrar sesión.";
    private final static String API_TOKEN_FIELD = "api_token";
    private final static String AUTH_ID_FIELD = "auth_id";

    public static boolean check(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILENAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(API_TOKEN_FIELD, null) != null;
    }

    public static String getApiTokenFromSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_FILENAME, Context.MODE_PRIVATE)
                .getString(API_TOKEN_FIELD, null);
    }

    public static int getAuthIdFromSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_FILENAME, Context.MODE_PRIVATE)
                .getInt(AUTH_ID_FIELD, 0);
    }

    public static void logout(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SP_FILENAME, Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(API_TOKEN_FIELD);

        if (editor.commit()) {
            redirectToLogin(context);
            return;
        }

        Toast.makeText(context, LOGOUT_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
    }

    public static void redirectToLogin(Context context) {
        Intent intent = new Intent(context, LoginScreenActivity.class);

        context.startActivity(intent);
    }
}
