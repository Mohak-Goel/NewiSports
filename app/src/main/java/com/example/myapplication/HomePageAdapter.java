package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.homePageViewHolder> {



    @NonNull
    @Override
    public homePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull homePageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class homePageViewHolder extends RecyclerView.ViewHolder {
        public homePageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
