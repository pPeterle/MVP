package app.mvp.session;

import android.content.Context;
import android.content.SharedPreferences;

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
        editor.putString("token", user.getToken());
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
}
