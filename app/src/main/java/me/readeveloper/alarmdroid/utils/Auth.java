package me.readeveloper.alarmdroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Auth {

    public static boolean check(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("alarmdroid.xml", Context.MODE_PRIVATE);
        return sharedPreferences.getString("api_token", null) != null;
    }
}
