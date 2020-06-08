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

public class HomePageAdapter extends FirebaseRecyclerAdapter<CreateEvent, HomePageAdapter.homePageViewHolder> {

    private OnItemClickListener mListener;
    private Context context;

    public HomePageAdapter (Context context, FirebaseRecyclerOptions<CreateEvent> options){

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
    public homePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_event_card, parent, false);
        homePageViewHolder homePageVH = new homePageViewHolder(v, mListener);
        return homePageVH;
    }

    @Override
    public void onBindViewHolder(@NonNull homePageViewHolder holder, int position, @org.jetbrains.annotations.NotNull CreateEvent createEvent) {

        holder.eventTitle.setText(createEvent.getEventName()+", "+ createEvent.getCity_Name());
        holder.eventTime.setText(createEvent.getChoose_Time());
        holder.eventDate.setText(createEvent.getEt_Date());

        Picasso.with(context).load(createEvent.getUrlLink()).into(holder.eventPoster);

        holder.participateNowButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ParticipationForm.class);
                context.startActivity(intent);

            }

        });

    }


    public static class homePageViewHolder extends RecyclerView.ViewHolder {

        public ImageView eventPoster;
        public TextView eventDate, eventTime, eventTitle;
        public Button participateNowButton;

        public homePageViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {

            super(itemView);

            eventPoster = itemView.findViewById(R.id.home_page_event_poster);
            eventDate = itemView.findViewById(R.id.home_page_event_date);
            eventTime = itemView.findViewById(R.id.home_page_event_time);
            eventTitle = itemView.findViewById(R.id.home_page_event_title);
            participateNowButton = itemView.findViewById(R.id.home_page_participate_button);

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
