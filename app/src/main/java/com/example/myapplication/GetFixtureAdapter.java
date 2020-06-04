package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GetFixtureAdapter extends RecyclerView.Adapter<GetFixtureAdapter.GetFixtureViewHolder> {

    private ArrayList<FixtureDetail> mFixtureList;

    @NonNull
    @Override
    public GetFixtureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_fixture_card, parent, false);
        GetFixtureViewHolder fixtureViewHolder = new GetFixtureViewHolder(v);
        return fixtureViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GetFixtureViewHolder holder, int position) {
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

    public static class GetFixtureViewHolder extends RecyclerView.ViewHolder {

        public TextView participant1VH, participant2VH, fixtureDateVH, fixtureTimeVH;

        public GetFixtureViewHolder(@NonNull View itemView) {
            super(itemView);
            participant1VH = itemView.findViewById(R.id.get_fixture_1);
            participant2VH = itemView.findViewById(R.id.get_fixture_2);
            fixtureDateVH = itemView.findViewById(R.id.get_fixture_date);
            fixtureTimeVH = itemView.findViewById(R.id.get_fixture_time);
        }
    }
    public GetFixtureAdapter (ArrayList<FixtureDetail> fixtureList){
        mFixtureList = fixtureList;
    }
}
