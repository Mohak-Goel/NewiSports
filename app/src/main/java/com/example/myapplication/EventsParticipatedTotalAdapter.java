package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventsParticipatedTotalAdapter extends FirebaseRecyclerAdapter<CreateEvent, EventsParticipatedTotalAdapter.EventsParticipatedTotalViewHolder> {

    private OnItemClickListener mListener;
    private Context context;
    private ArrayList<CreateEvent> arrayList;

    public EventsParticipatedTotalAdapter(Context context, FirebaseRecyclerOptions<CreateEvent> options){

        super(options);
        this.context = context;
        arrayList = new ArrayList<>();

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
    public void onBindViewHolder(@NonNull  EventsParticipatedTotalViewHolder holder, final int position, CreateEvent createEvent) {

        holder.eventTitle.setText(createEvent.getEventName());
        holder.eventTime.setText(createEvent.getChoose_Time());
        holder.eventDate.setText(createEvent.getEt_Date());

        Picasso.with(context).load(createEvent.getUrlLink()).into(holder.eventPoster);

        arrayList.add(new CreateEvent(createEvent.getEventName(), createEvent.getField_Name(), createEvent.getCity_Name(), createEvent.getPostal_Code(), createEvent.getSports_Name(), createEvent.getChoose_Time(), createEvent.getEt_Date(), createEvent.isFood(), createEvent.isLodging(), createEvent.isTransport(), createEvent.getEventDescription(), createEvent.getOurContact(), createEvent.getUrlLink()));

        holder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EventsParticipated.class);
                intent.putExtra("My Event Participated Detail", arrayList.get(position));
                context.startActivity(intent);

            }
        });
    }


    public static class  EventsParticipatedTotalViewHolder extends RecyclerView.ViewHolder {

        public ImageView eventPoster;
        public TextView eventDate, eventTime, eventTitle;
        public Button viewDetail;


        public  EventsParticipatedTotalViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {

            super(itemView);

            eventPoster = itemView.findViewById(R.id.event_participated_poster);
            eventDate = itemView.findViewById(R.id.event_participated_date);
            eventTime = itemView.findViewById(R.id.event_participated_time);
            eventTitle = itemView.findViewById(R.id.event_participated_title);
            viewDetail = itemView.findViewById(R.id.event_participated_view_detail);

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
