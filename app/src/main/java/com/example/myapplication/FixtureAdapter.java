package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.FixtureViewHolder> {

    private ArrayList<FixtureDetail> mFixtureList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class FixtureViewHolder extends RecyclerView.ViewHolder {
        public TextView participant1VH, participant2VH, fixtureTimeVH,fixtureDateVH;
        public ImageView deleteButton;

        public FixtureViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            participant1VH = itemView.findViewById(R.id.fixture_1);
            participant2VH = itemView.findViewById(R.id.fixture_2);
           // vsVH = itemView.findViewById(R.id.text_view_vs);
            fixtureDateVH = itemView.findViewById(R.id.fixture_date);
            fixtureTimeVH = itemView.findViewById(R.id.fixture_time);
            deleteButton = itemView.findViewById(R.id.fixture_delete);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }

    public FixtureAdapter (ArrayList<FixtureDetail> fixtureList){
        mFixtureList = fixtureList;
    }

    @Override
    public FixtureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixture_card, parent, false);
        FixtureViewHolder fixtureViewHolder = new FixtureViewHolder(v, mListener);
        return fixtureViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull FixtureViewHolder holder, int position) {

        FixtureDetail currentItem = mFixtureList.get(position);

        holder.participant1VH.setText(currentItem.getParticipant1());
        holder.participant2VH.setText(currentItem.getParticipant2());
        holder.fixtureDateVH.setText(currentItem.getFixtureDate());
        holder.fixtureTimeVH.setText(currentItem.getFixtureTime());

    }

    @Override
    public int getItemCount() {
        return mFixtureList.size();
    }

}
