package app.mvp.ui.activity.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import app.mvp.R;
import app.mvp.adapter.UserProfileAdapter;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ListView listView = findViewById(R.id.listView);

        UserProfileAdapter adapter = new UserProfileAdapter(this);
        adapter.addItem("Nome de preferência", "John");
        adapter.addItem("E-mail", "contato@jonathansilva.net");
        adapter.addItem("Telefone", "(00) 00000-0000");
        adapter.addItem("Alterar senha", null);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.stable, R.anim.left_to_right);
    }
}
