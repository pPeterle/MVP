package app.mvp.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import app.mvp.model.server.ConnectivityInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {
    private static Retrofit retrofit = null;

    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    static Retrofit getClient(String url) {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES);

            httpClient.addInterceptor(logging);
            httpClient.addInterceptor(new ConnectivityInterceptor(context));

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
