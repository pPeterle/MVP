package app.mvp.di.component;

import javax.inject.Singleton;

import app.mvp.di.ActivityBuilder;
import dagger.Component;

@Singleton
@Component(modules = {ActivityBuilder.class})
public interface AppComponent {


}
