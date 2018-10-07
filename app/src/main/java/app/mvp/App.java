package app.mvp;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import app.mvp.di.component.DaggerAppComponent;
import app.mvp.session.Session;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }


    /*private Session session;

    public Session getSession() {
        if (session == null) {
            session = new Session(getApplicationContext());
        }
        return session;
    }*/
}
