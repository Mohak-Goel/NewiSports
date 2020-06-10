package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyviewHolder2 extends RecyclerView.ViewHolder{
    TextView universitynam,universitycname;
    public MyviewHolder2(@NonNull View itemView) {
        super(itemView);

        universitynam=itemView.findViewById(R.id.universityn1);
        universitycname=itemView.findViewById(R.id.universitycoachname);
    }
}
