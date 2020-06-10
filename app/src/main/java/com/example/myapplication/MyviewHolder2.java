package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyviewHolder2 extends RecyclerView.ViewHolder{
    TextView universityname,universitycoachname;
    public MyviewHolder2(@NonNull View itemView) {
        super(itemView);

        universityname=itemView.findViewById(R.id.universityName);
        universitycoachname=itemView.findViewById(R.id.universitycoachname);
    }
}
