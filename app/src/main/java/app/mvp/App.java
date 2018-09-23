package app.mvp;

import android.app.Application;

import app.mvp.session.Session;

public class App extends Application {
    private Session session;

    public Session getSession() {
        if (session == null) {
            session = new Session(getApplicationContext());
        }
        return session;
    }
}
