package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class EventsParticipatedTotalAdapter extends FirebaseRecyclerAdapter<CreateEvent, EventsParticipatedTotalAdapter.EventsParticipatedTotalViewHolder> {

    private OnItemClickListener mListener;
    private Context context;

    public EventsParticipatedTotalAdapter(Context context, FirebaseRecyclerOptions<CreateEvent> options){

        super(options);
        this.context = context;

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public EventsParticipatedTotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_events_participated_card, parent, false);
        EventsParticipatedTotalViewHolder evptVH = new  EventsParticipatedTotalViewHolder(v, mListener);
        return evptVH;
    }

    @Override
    public void onBindViewHolder(@NonNull  EventsParticipatedTotalViewHolder holder, int position, CreateEvent createEvent) {

        holder.eventTitle.setText(createEvent.getEventName());
        holder.eventTime.setText(createEvent.getChoose_Time());
        holder.eventDate.setText(createEvent.getEt_Date());

        Picasso.with(context).load(createEvent.getUrlLink()).into(holder.eventPoster);


    }


    public static class  EventsParticipatedTotalViewHolder extends RecyclerView.ViewHolder {

        public ImageView eventPoster;
        public TextView eventDate, eventTime, eventTitle;


        public  EventsParticipatedTotalViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {

            super(itemView);

            eventPoster = itemView.findViewById(R.id.event_participated_poster);
            eventDate = itemView.findViewById(R.id.event_participated_date);
            eventTime = itemView.findViewById(R.id.event_participated_time);
            eventTitle = itemView.findViewById(R.id.event_participated_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION)
                            mListener.onItemClick(position);
                    }
                }
            });

        }
    }


}
