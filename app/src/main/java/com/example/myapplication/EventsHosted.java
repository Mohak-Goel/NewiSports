package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class EventsHosted extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_hosted);

        ArrayList<ExampleItem> exampleList=new ArrayList<>();
        exampleList.add(new ExampleItem("Line 1","Line 2"));
        exampleList.add(new ExampleItem("Line 3","Line 4"));
    }
}
