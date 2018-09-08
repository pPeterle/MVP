package app.mvp.ui.activity.establishment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import app.mvp.R;
import app.mvp.database.Database;

public class ListEstablishmentsActivity extends AppCompatActivity {
    public Database database;
    public Toolbar toolbar;
    public ProgressBar progress;
    public RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_establishment);

        toolbar.setTitle("Estabelecimento:");
        setSupportActionBar(toolbar);

        database = new Database(this);

        getEstablishments();
    }

    public void getEstablishments() {
        //database.open();

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        EstablishmentsAdapter adapter = new EstablishmentsAdapter(database.getEstablishments());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));*/

        //database.close();
    }
}
