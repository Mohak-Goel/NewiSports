package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyviewHolder extends RecyclerView.ViewHolder{
    TextView textViewUniversityName,textViewNotice;
    public MyviewHolder(@NonNull View itemView) {
        super(itemView);
        textViewUniversityName=itemView.findViewById(R.id.textviewUniversityName);
        textViewNotice=itemView.findViewById(R.id.textviewNotice);
    }
}
