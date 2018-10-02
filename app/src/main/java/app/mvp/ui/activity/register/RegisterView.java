package app.mvp.ui.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.mvp.R;
import app.mvp.helper.FragmentHelper;
import app.mvp.ui.activity.home.HomeView;
import app.mvp.ui.fragment.register.NameRegisterView;

public class RegisterView extends AppCompatActivity implements RegisterContract.RegisterView {
    private RegisterContract.RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (presenter == null) {
            presenter = new RegisterPresenter(this);
        }
    }

    @Override
    public void initView() {
        FragmentHelper.load(new NameRegisterView(), true, new Bundle(), this);
    }

    @Override
    public void openHome() {
        Intent intent = new Intent(this, HomeView.class);
        startActivity(intent);
        RegisterView.this.finish();
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
