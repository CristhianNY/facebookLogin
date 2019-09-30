package com.cristhianbonilla.com.vivikey.core.domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreference {
    public static final String Id = "idKey";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Phone = "phoneKey";
    public static final String IsRegistered = "isRegistered";

    public static User getUser(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        User user = new User(preferences.getString(Id,""),
                preferences.getString(Name,""),
                preferences.getString(Email,""),
                preferences.getString(Phone,""),
                preferences.getBoolean(IsRegistered,false));
        return user;
    }

    public static void saveUser(User user, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Id, user.getId());
        editor.putString(Name, user.getUsername());
        editor.putString(Email, user.getEmail());
        editor.putString(Phone, user.getPhone());
        editor.putBoolean(IsRegistered, user.getIsRegistered());
        editor.apply();
    }

    public static void DeleteUser(User user, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(Id);
        editor.remove(Name);
        editor.remove(Email);
        editor.remove(Phone);
        editor.remove(IsRegistered);
        editor.apply();
    }
}
