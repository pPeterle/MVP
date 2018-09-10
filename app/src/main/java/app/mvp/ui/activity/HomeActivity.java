package app.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import app.mvp.R;

public class HomeActivity extends AppCompatActivity implements HomeContract.HomeView {
    public Intent intent;

    private HomeContract.HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (presenter == null) {
            presenter = new HomePresenter(this);
        }
    }

    protected void onStart() {
        super.onStart();

        Window window = getWindow(); window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public void initView() {
        ImageView imageView = findViewById(R.id.imageView_play_video);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        imageView.startAnimation(pulse);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(login);

        Button btn_register_client = findViewById(R.id.btn_register_client);
        btn_register_client.setOnClickListener(registerClient);

        Button btn_register_owner = findViewById(R.id.btn_register_owner);
        btn_register_owner.setOnClickListener(registerOwner);
    }

    private View.OnClickListener registerClient = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.registerClient();
        }
    };

    private View.OnClickListener registerOwner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.registerOwner();
        }
    };

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.login();
        }
    };

    @Override
    public void abreLogin() {
        intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        HomeActivity.this.finish();
    }

    @Override
    public void abreRegisterClient() {
        intent = new Intent(HomeActivity.this, RegisterActivity.class);
        startActivity(intent);
        HomeActivity.this.finish();
    }

    @Override
    public void abreRegisterOwner() {
        // TODO
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
