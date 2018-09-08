package app.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.mvp.R;
import app.mvp.helper.FragmentHelper;
import app.mvp.ui.fragment.login.PhoneLoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        FragmentHelper.load(new PhoneLoginFragment(), true, new Bundle(), this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
