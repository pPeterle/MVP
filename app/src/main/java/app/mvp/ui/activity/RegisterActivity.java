package app.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.mvp.R;
import app.mvp.helper.FragmentHelper;
import app.mvp.ui.fragment.register.NameRegisterFragment;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FragmentHelper.load(new NameRegisterFragment(), true, new Bundle(), this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            RegisterActivity.this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
