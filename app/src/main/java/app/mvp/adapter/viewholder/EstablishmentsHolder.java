package app.mvp.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import app.mvp.R;

public class EstablishmentsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView uuid;
    public TextView name;
    public TextView city;

    public EstablishmentsHolder(View view) {
        super(view);

        uuid = view.findViewById(R.id.uuid);
        name = view.findViewById(R.id.name);
        city = view.findViewById(R.id.city);

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO
    }
}
