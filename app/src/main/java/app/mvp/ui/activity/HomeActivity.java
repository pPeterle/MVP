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

        onClick();
    }

    protected void onStart() {
        super.onStart();

        Window window = getWindow(); window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        ImageView imageView = findViewById(R.id.imageView_play_video);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        imageView.startAnimation(pulse);
    }

    private void onClick() {
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(login);

        Button btn_register_client = findViewById(R.id.btn_register_client);
        btn_register_client.setOnClickListener(registerClient);

        Button btn_register_owner = findViewById(R.id.btn_register_owner);
        btn_register_owner.setOnClickListener(registerOwner);
    }

    private View.OnClickListener registerClient = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(HomeActivity.this, RegisterActivity.class);
            startActivity(intent);
            HomeActivity.this.finish();
        }
    };

    private View.OnClickListener registerOwner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            HomeActivity.this.finish();
        }
    };

    // MVP

    @Override
    public void abreLogin() {

    }

    @Override
    public void abreRegisterClient() {

    }

    @Override
    public void abreRegisterOwner() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
