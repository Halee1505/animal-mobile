package com.funix.animal.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SharedPreferencesController {
    private static final String PREF_NAME = "AnimalPrefs";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesController(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Method to save a string value
    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    // Method to get a string value
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    // Method to save an integer value
    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    // Method to get an integer value
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    // Method to save a boolean value
    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    // Method to get a boolean value
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    // Method to remove a value
    public void remove(String key) {
        editor.remove(key);
        editor.apply();
    }
    public String getKeyFromStringValue(String value) {
        Map<String, ?> allEntries = sharedPreferences.getAll();
        System.out.println(value);
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getValue() instanceof String && entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
    // Method to clear all values
    public void clear() {
        editor.clear();
        editor.apply();
    }
}
