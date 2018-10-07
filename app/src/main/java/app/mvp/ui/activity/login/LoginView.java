package app.mvp.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import app.mvp.R;
import app.mvp.helper.FragmentHelper;
import app.mvp.ui.activity.home.HomeView;
import app.mvp.ui.fragment.login.PhoneLoginView;
import dagger.android.AndroidInjection;

public class LoginView extends AppCompatActivity implements LoginContract.LoginView {

    @Inject
    LoginContract.LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        if (presenter == null) {
            presenter = new LoginPresenter(this);
        }
    }

    @Override
    public void initView() {
        FragmentHelper.load(new PhoneLoginView(), true, new Bundle(), this);
    }

    @Override
    public void openHome() {
        Intent intent = new Intent(this, HomeView.class);
        startActivity(intent);
        LoginView.this.finish();
    }

    @Override
    public void popBackStack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int count = getSupportFragmentManager().getBackStackEntryCount();
        presenter.onBackPressed(count);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
