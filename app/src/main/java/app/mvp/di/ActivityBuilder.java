package app.mvp.di;

import app.mvp.ui.fragment.login.PasswordLoginView;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    public abstract PasswordLoginView bindPasswordLoginView();
}
