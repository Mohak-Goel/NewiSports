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

public class EventCreatedTotalAdapter extends FirebaseRecyclerAdapter<CreateEvent, EventCreatedTotalAdapter.EventCreatedTotalViewHolder> {

    private OnItemClickListener mListener;
    private Context context;
    private ArrayList<CreateEvent> arrayList;
    private String user;

    public EventCreatedTotalAdapter (Context context, String user_type ,FirebaseRecyclerOptions<CreateEvent> options){

        super(options);
        this.context = context;
        user=user_type;
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
    public EventCreatedTotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_event_created_card, parent, false);
        EventCreatedTotalViewHolder evctVH = new  EventCreatedTotalViewHolder(v, mListener);
        return evctVH;
    }

    @Override
    public void onBindViewHolder(@NonNull  EventCreatedTotalViewHolder holder, final int position, CreateEvent createEvent) {

        holder.eventTitle.setText(createEvent.getEventName());
        holder.eventTime.setText(createEvent.getChoose_Time());
        holder.eventDate.setText(createEvent.getEt_Date());

        Picasso.with(context).load(createEvent.getUrlLink()).into(holder.eventPoster);

        arrayList.add(new CreateEvent(createEvent.getEventName(), createEvent.getField_Name(), createEvent.getCity_Name(), createEvent.getPostal_Code(), createEvent.getSports_Name(), createEvent.getChoose_Time(), createEvent.getEt_Date(), createEvent.isFood(), createEvent.isLodging(), createEvent.isTransport(), createEvent.getEventDescription(), createEvent.getOurContact(), createEvent.getUrlLink(), createEvent.getStatus()));

        holder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MyEventsCreated.class);
                intent.putExtra("Event Created Details Adapter", arrayList.get(position));
                intent.putExtra("User Type", user);
                context.startActivity(intent);

            }
        });

    }


    public static class  EventCreatedTotalViewHolder extends RecyclerView.ViewHolder {

        public ImageView eventPoster;
        public TextView eventDate, eventTime, eventTitle;
        public Button viewDetail;


        public  EventCreatedTotalViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {

            super(itemView);

            eventPoster = itemView.findViewById(R.id.event_created_poster);
            eventDate = itemView.findViewById(R.id.event_created_date);
            eventTime = itemView.findViewById(R.id.event_created_time);
            eventTitle = itemView.findViewById(R.id.event_created_title);
            viewDetail = itemView.findViewById(R.id.event_created_view_detail);

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
