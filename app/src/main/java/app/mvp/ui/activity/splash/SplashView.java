package app.mvp.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import app.mvp.R;
import app.mvp.session.Session;
import app.mvp.ui.activity.dashboard.DashboardView;
import app.mvp.ui.activity.home.HomeView;

public class SplashView extends AppCompatActivity implements SplashContract.SplashView {
    private SplashContract.SplashPresenter presenter;

    public Handler handler;
    public Intent intent;
    public Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        session = new Session(this);

        if (presenter == null) {
            presenter = new SplashPresenter(this);
        }
    }

    @Override
    public void initView() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.callSession(session);
            }
        }, 1000);
    }

    @Override
    public void openHome() {
        intent = new Intent(SplashView.this, HomeView.class);
        startActivity(intent);
        SplashView.this.finish();
    }

    @Override
    public void openDashboard() {
        intent = new Intent(SplashView.this, DashboardView.class);
        startActivity(intent);
        SplashView.this.finish();

        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
