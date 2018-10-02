package app.mvp;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import app.mvp.model.server.ConnectivityInterceptor;
import app.mvp.session.Session;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private Session session;

    public Session getSession() {
        if (session == null) {
            session = new Session(getApplicationContext());
        }
        return session;
    }
}
