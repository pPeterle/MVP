package app.mvp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.mvp.R;
import app.mvp.adapter.viewholder.EstablishmentsHolder;
import app.mvp.model.Establishment;

public class EstablishmentsAdapter extends RecyclerView.Adapter<EstablishmentsHolder> {
    private final ArrayList<Establishment> mEstablishments;

    public EstablishmentsAdapter(ArrayList<Establishment> establishments) {
        mEstablishments = establishments;
    }

    @NonNull
    @Override
    public EstablishmentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EstablishmentsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_establishment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EstablishmentsHolder holder, final int position) {
        holder.uuid.setText(mEstablishments.get(position).getUUID());
        holder.name.setText(mEstablishments.get(position).getName());
        holder.city.setText(mEstablishments.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return mEstablishments != null ? mEstablishments.size() : 0;
    }
}
