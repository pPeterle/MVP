package app.mvp.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import app.mvp.model.User;

public class Session {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private static final int PRIVATE_MODE = 0;

    public Session(Context context) {
        pref = context.getSharedPreferences("pref", PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    public void setLogin(User user) {
        editor.putString(encrypt("token"), encrypt(user.getToken()));
        editor.putString("uuid", user.getUUID());
        editor.putString("establishment_uuid", user.getEstablishment());
        editor.putString("profile", user.getProfile());
        editor.putString("nickname", user.getNickname());
        editor.putBoolean("logged", true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean("logged", false);
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }

    public static String encrypt(String input) {
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    public static String decrypt(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }
}
