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

public class EventCreatedTotalAdapter extends FirebaseRecyclerAdapter<CreateEvent, EventCreatedTotalAdapter.EventCreatedTotalViewHolder> {

    private OnItemClickListener mListener;
    private Context context;

    public EventCreatedTotalAdapter (Context context, FirebaseRecyclerOptions<CreateEvent> options){

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
    public EventCreatedTotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_event_created_card, parent, false);
        EventCreatedTotalViewHolder evctVH = new  EventCreatedTotalViewHolder(v, mListener);
        return evctVH;
    }

    @Override
    public void onBindViewHolder(@NonNull  EventCreatedTotalViewHolder holder, int position, CreateEvent createEvent) {

        holder.eventTitle.setText(createEvent.getEventName());
        holder.eventTime.setText(createEvent.getChoose_Time());
        holder.eventDate.setText(createEvent.getEt_Date());

        Picasso.with(context).load(createEvent.getUrlLink()).into(holder.eventPoster);


    }


    public static class  EventCreatedTotalViewHolder extends RecyclerView.ViewHolder {

        public ImageView eventPoster;
        public TextView eventDate, eventTime, eventTitle;


        public  EventCreatedTotalViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {

            super(itemView);

            eventPoster = itemView.findViewById(R.id.event_created_poster);
            eventDate = itemView.findViewById(R.id.event_created_date);
            eventTime = itemView.findViewById(R.id.event_created_time);
            eventTitle = itemView.findViewById(R.id.event_created_title);

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
