package app.mvp.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {

    @Provides
    Context provideContext(Application application) {
        return application;
    }

    /*@Provides
    private Retrofit retrofit() {
        // TODO
    }*/
}
