package app.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import app.mvp.R;
import app.mvp.session.Session;
import app.mvp.ui.activity.dashboard.DashboardActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.SplashView {
    public Handler handler;
    public Intent intent;
    public Session session;

    private SplashContract.SplashPresenter presenter;

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
    public void onStart() {
        super.onStart();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Verifica se o usuário está logado e depois, qual activity abrir (Home ou Dashboard)
                        presenter.logged(session);
                    }
                });
            }
        }, 1000);
    }

    @Override
    public void abreHome() {
        intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void abreDashboard() {
        intent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();

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
        presenter.destroy();
    }
}
