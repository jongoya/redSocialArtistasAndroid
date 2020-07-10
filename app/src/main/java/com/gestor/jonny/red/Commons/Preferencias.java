package com.gestor.jonny.red.Commons;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    private static final String preferencesName = "MisPreferencias";

    public static String getUserName(Context contexto) {
        SharedPreferences prefs = contexto.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        return prefs.getString("usuario", "");
    }

    public static String getPassword(Context contexto) {
        SharedPreferences prefs = contexto.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        return prefs.getString("contra", "");
    }

    public static void saveUserName(Context contexto, String username) {
        SharedPreferences prefs = contexto.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("usuario", username).apply();
    }

    public static void savePassword(Context contexto, String username) {
        SharedPreferences prefs = contexto.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("contra", username).apply();
    }

    public static boolean checkUserCredentials(Context contexto) {
        SharedPreferences prefs = contexto.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        if (prefs.getString("usuario", "").length() == 0) {
            return false;
        }

        return true;
    }

    public static void removeUserCredentials(Context contexto){
        SharedPreferences prefs = contexto.getSharedPreferences(preferencesName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("usuario");
        editor.remove("contra");
    }
}
