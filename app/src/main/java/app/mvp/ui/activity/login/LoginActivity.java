package app.mvp.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.mvp.R;
import app.mvp.helper.FragmentHelper;
import app.mvp.ui.activity.home.HomeActivity;
import app.mvp.ui.fragment.login.PhoneLoginFragment;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {
    private LoginContract.LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        if (presenter == null) {
            presenter = new LoginPresenter(this);
        }
    }

    @Override
    public void initView() {
        FragmentHelper.load(new PhoneLoginFragment(), true, new Bundle(), this);
    }

    @Override
    public void openHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
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
