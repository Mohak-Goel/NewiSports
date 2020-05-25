package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.participantViewHolder> {
    private ArrayList<ParticipantItem> mParticipantsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class participantViewHolder extends RecyclerView.ViewHolder{
        public TextView participantName, bloodGroup, participantEmail, participantPhNo;
        public ImageView deleteButton;

        public participantViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            participantName = itemView.findViewById(R.id.participant_name);
            participantEmail = itemView.findViewById(R.id.participant_email);
            participantPhNo = itemView.findViewById(R.id.participant_phoneNo);
            bloodGroup = itemView.findViewById(R.id.participant_bg);
            deleteButton = itemView.findViewById(R.id.image_delete);

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

    public ParticipantsAdapter(ArrayList<ParticipantItem> participantList){
        mParticipantsList = participantList;
    }

    @Override
    public participantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_card, parent, false);
        participantViewHolder pvh = new participantViewHolder(v, mListener);
        return pvh;

    }

    @Override
    public void onBindViewHolder(@NonNull participantViewHolder holder, int position) {
        ParticipantItem currentItem = mParticipantsList.get(position);

        holder.participantName.setText(currentItem.getParticipantName());
        holder.participantEmail.setText(currentItem.getParticipantEmail());
        holder.participantPhNo.setText(currentItem.getParticipantPhNo());
        holder.bloodGroup.setText(currentItem.getBloodGroup());
    }

    @Override
    public int getItemCount() {
        return mParticipantsList.size();
    }
}
