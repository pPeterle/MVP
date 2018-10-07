package app.mvp.di.component;

import android.app.Application;

import javax.inject.Singleton;

import app.mvp.App;
import app.mvp.di.ActivityBuilder;
import app.mvp.di.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {ActivityBuilder.class, AppModule.class, AndroidInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application (Application application);
        AppComponent build();
    }

    void inject(App app);
}
